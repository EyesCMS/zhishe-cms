package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.date.DateUtil;
import edu.fzu.zhishe.cms.mapper.*;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.CheckinStateEnum;
import edu.fzu.zhishe.core.constant.CreditEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.constant.PostTypeEnum;
import edu.fzu.zhishe.core.dto.HonorDTO;
import edu.fzu.zhishe.core.error.CreditErrorEnum;
import edu.fzu.zhishe.core.error.CreditErrorEnum;
import edu.fzu.zhishe.core.service.CreditService;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.core.util.CreditUtil;
import edu.fzu.zhishe.core.util.NotExistUtil;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author yang on 5/18/2020.
 * @version 1.0
 */
@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CmsClubMapper cmsClubMapper;

    @Autowired
    private CmsUserClubRelMapper cmsUserClubRelMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FmsPostMapper fmsPostMapper;

    @Autowired
    private FmsPostRemarkMapper fmsPostRemarkMapper;

    @Autowired
    private CmsMemberHonorMapper cmsMemberHonorMapper;

    @Autowired
    private CmsClubGradeMapper cmsClubGradeMapper;

    @Autowired
    CmsCreditCacheServiceImpl creditCacheService;

    //最高可获得积分评论数
    private final int MAX_COMMENT_NUM = 1;

    //最高可获得积分评论数
    private final int MAX_CREDIT_ONEDAY = 50;

    @Override
    public void creditAdd(CmsUserClubRel cmsUserClubRel, int credit) {
        Integer creditForToday = creditCacheService.getTodayCredit(
            cmsUserClubRel.getClubId(), cmsUserClubRel.getUserId());
        if (creditForToday != null) {
            if ((creditForToday+credit) > MAX_CREDIT_ONEDAY) {
                System.out.println("超出每日积分上限");
                return;
            }
        }
        int oldCredit = cmsUserClubRel.getCredit();
        oldCredit += credit;
        cmsUserClubRel.setCredit(oldCredit);
        List<Integer> lowerBounds = cmsMemberHonorMapper.selectByExample(null)
            .stream()
            .map(CmsMemberHonor :: getLowerLimit)
            .collect(Collectors.toList());
        cmsUserClubRel.setHonorId(CreditUtil.getGradeByCredit(lowerBounds, oldCredit));
        if(cmsUserClubRelMapper.updateByPrimaryKeySelective(cmsUserClubRel) == 0){
            Asserts.fail(CreditErrorEnum.MAPPER_OPERATION_FAILED);
        }

        // store today's credit to cache
        creditCacheService.incrTodayCredit(
            cmsUserClubRel.getClubId(), cmsUserClubRel.getUserId(), (long) credit);
    }

    @Override
    public CmsUserClubRel honorCalculate(CmsUserClubRel cmsUserClubRel) {
        int credit = cmsUserClubRel.getCredit();
        List<CmsMemberHonor> memberHonorList = cmsMemberHonorMapper.selectByExample(null);
        for(CmsMemberHonor memberHonor:memberHonorList){
            if (memberHonor.getLowerLimit() <= credit && credit < memberHonor.getUpperLimit()) {
                cmsUserClubRel.setHonorId(memberHonor.getId());
                return cmsUserClubRel;
            }
        }
        Asserts.fail("无法计算活跃度，积分不在任何一个规定活跃度的区间");
        return null;
    }

    @Override
    public void checkin(Integer clubId,Date date) {
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andUserIdEqualTo(sysUserService.getCurrentUser().getId())
                .andClubIdEqualTo(clubId);
        List<CmsUserClubRel> userClubRelList = cmsUserClubRelMapper.selectByExample(example);
        int state = isCheckin(clubId,date,userClubRelList);
        if (state == CheckinStateEnum.DENIED.getValue()) {
            Asserts.fail(CreditErrorEnum.USER_CLUB_REL_NOT_EXIST);
        }
        if (state == CheckinStateEnum.DONE.getValue()) {
            Asserts.forbidden(CreditErrorEnum.ALREADY_CHECKIN);
        }
        if (state == CheckinStateEnum.GRANTED.getValue()){
            CmsUserClubRel userClubRel = userClubRelList.get(0);
            userClubRel.setCheckInDate(date);
            creditAdd(userClubRel, CreditEnum.CHECKIN.getValue());
        }

    }

    @Override
    public int isCheckin(Integer clubId,Date date,List<CmsUserClubRel> userClubRelList) {
        //0：今天签过到不可签到，1：可以签到，2：没有相关userclubrel表记录
        //没有记录
        if (CollectionUtils.isEmpty(userClubRelList)) {
            return CheckinStateEnum.DENIED.getValue();
        }
        CmsUserClubRel userClubRel = userClubRelList.get(0);
        Date lastCheckinDate = userClubRel.getCheckInDate();
        //从来没签到过
        if (lastCheckinDate == null) {
            return CheckinStateEnum.GRANTED.getValue();
        }
        //今天已经签过到
        if (DateUtil.isSameDay(date, lastCheckinDate)) {
            return CheckinStateEnum.DONE.getValue();
        }

        return CheckinStateEnum.GRANTED.getValue();
    }

    @Override
    public void getCreditByComment(Integer postId) {
        FmsPostRemarkExample example = new FmsPostRemarkExample();
        example.createCriteria().andPostIdEqualTo(postId)
            .andUserIdEqualTo(sysUserService.getCurrentUser().getId());
        List<FmsPostRemark> remarkList = fmsPostRemarkMapper.selectByExample(example);
        //没有评论或者同一帖子评论超过两条不增加积分
        if (CollectionUtils.isEmpty(remarkList) || remarkList.size() > MAX_COMMENT_NUM) {
            System.out.println("没有评论或者同一帖子评论超过两条不增加积分");
            return;
        }
        FmsPost post = fmsPostMapper.selectByPrimaryKey(postId.longValue());
        if (NotExistUtil.check(post)) {
            Asserts.fail(CreditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        //个人贴不加积分
        if (post.getType().equals(PostTypeEnum.PERSONAL.getValue())) {
            System.out.println("个人贴不加积分");
            return;
        }
        //非用户已加入社团不加积分
        int clubId = post.getPosterId();
        CmsUserClubRelExample example1 = new CmsUserClubRelExample();
        example1.createCriteria().andUserIdEqualTo(sysUserService.getCurrentUser().getId())
            .andClubIdEqualTo(clubId);
        List<CmsUserClubRel> userClubRelList = cmsUserClubRelMapper.selectByExample(example1);
        if (CollectionUtils.isEmpty(userClubRelList)) {
            System.out.println("非用户已加入社团不加积分");
            return;
        }
        //种种情况考虑之后进行加分
        CmsUserClubRel userClubRel = userClubRelList.get(0);
        creditAdd(userClubRel, CreditEnum.COMMENT.getValue());
    }

    @Override
    public HonorDTO getUserHonor(Integer clubId) {
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andUserIdEqualTo(sysUserService.getCurrentUser().getId())
            .andClubIdEqualTo(clubId);
        List<CmsUserClubRel> userClubRelList = cmsUserClubRelMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userClubRelList)) {
            Asserts.fail(CreditErrorEnum.USER_CLUB_REL_NOT_EXIST);
        }
        CmsUserClubRel userClubRel = userClubRelList.get(0);
        CmsMemberHonor myHonor = cmsMemberHonorMapper.selectByPrimaryKey(userClubRel.getHonorId());
        if (myHonor == null) {
            Asserts.fail(CreditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        String grade = myHonor.getName();
        int score = userClubRel.getCredit();
        double total = myHonor.getUpperLimit() - myHonor.getLowerLimit();
        double numerator = score - myHonor.getLowerLimit();
        double percent = (numerator / total) * 100;
        int percentage = new Double(percent).intValue();
        HonorDTO honorDTO = new HonorDTO();
        honorDTO.setGrade(grade);
        honorDTO.setScore(score);
        honorDTO.setPercentage(percentage);
        return honorDTO;
    }

    @Override
    public HonorDTO getClubGrade(Integer clubId) {
        CmsClub cmsClub = cmsClubMapper.selectByPrimaryKey(clubId);
        if (cmsClub == null|| cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()) {
            Asserts.fail(CreditErrorEnum.CLUB_NOT_EXIST);
        }
        CmsClubGrade myGrade = cmsClubGradeMapper.selectByPrimaryKey(cmsClub.getGradeId());
        if (myGrade == null) {
            Asserts.fail(CreditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        String grade = myGrade.getName();
        int score = cmsClub.getCredit();
        double total = myGrade.getUpperLimit() - myGrade.getLowerLimit();
        double numerator = score - myGrade.getLowerLimit();
        double percent = (numerator / total) * 100;
        int percentage = new Double(percent).intValue();
        HonorDTO honorDTO = new HonorDTO();
        honorDTO.setGrade(grade);
        honorDTO.setScore(score);
        honorDTO.setPercentage(percentage);
        return honorDTO;
    }

    @Override
    public List<CmsMemberHonor> listMemberHonor() {
        return cmsMemberHonorMapper.selectByExample(null);
    }

    @Override
    public List<CmsClubGrade> listClubGrade() {
        return cmsClubGradeMapper.selectByExample(null);
    }

    @Override
    public CmsClub getCreditByActivity(Integer clubId) {
        CmsClub cmsClub = cmsClubMapper.selectByPrimaryKey(clubId);
        if (cmsClub == null) {
            Asserts.fail(CreditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        clubCreditAdd(cmsClub,CreditEnum.ACTIVITY.getValue());
        return cmsClub;
    }

    @Override
    public void clubCreditAdd(CmsClub cmsClub, int credit) {
        int oldCredit = cmsClub.getCredit();
        oldCredit += credit;
        cmsClub.setCredit(oldCredit);
        List<Integer> lowerBounds = cmsClubGradeMapper.selectByExample(null)
            .stream()
            .map(CmsClubGrade::getLowerLimit)
            .collect(Collectors.toList());
        cmsClub.setGradeId(CreditUtil.getGradeByCredit(lowerBounds, credit));
        if(cmsClubMapper.updateByPrimaryKeySelective(cmsClub) == 0){
            Asserts.fail(CreditErrorEnum.MAPPER_OPERATION_FAILED);
        }
    }

    @Override
    public CmsClub clubCreditCalculate(CmsClub cmsClub) {
        int credit = cmsClub.getCredit();
        List<CmsClubGrade> clubGradeList = cmsClubGradeMapper.selectByExample(null);
        for (CmsClubGrade clubGrade : clubGradeList) {
            if (clubGrade.getLowerLimit() <= credit && credit <= clubGrade.getUpperLimit()) {
                cmsClub.setGradeId(clubGrade.getId());
                return cmsClub;
            }
        }
        Asserts.fail("无法计算，积分不在任何一个规定的区间");
        return null;
    }

}
