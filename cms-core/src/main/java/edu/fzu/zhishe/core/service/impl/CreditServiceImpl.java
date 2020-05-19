package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.date.DateUtil;
import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.mapper.FmsPostRemarkMapper;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.CheckinStateEnum;
import edu.fzu.zhishe.core.constant.CreditEnum;
import edu.fzu.zhishe.core.constant.PostTypeEnum;
import edu.fzu.zhishe.core.param.CreditForCheckinParam;
import edu.fzu.zhishe.core.service.CreditService;
import edu.fzu.zhishe.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * @author yang on 5/18/2020.
 * @version 1.0
 */
@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CmsUserClubRelMapper cmsUserClubRelMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FmsPostMapper fmsPostMapper;

    @Autowired
    private FmsPostRemarkMapper fmsPostRemarkMapper;

    //最高可获得积分评论数
    private final int maxCommentCreditNum = 2;

    @Override
    public void creditAdd(CmsUserClubRel cmsUserClubRel, int credit) {
        int oldCredit = cmsUserClubRel.getCredit();
        oldCredit += credit;
        cmsUserClubRel.setCredit(oldCredit);
        cmsUserClubRelMapper.updateByPrimaryKeySelective(cmsUserClubRel);
    }

    @Override
    public void checkin(Integer clubId,Date date) {
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andUserIdEqualTo(sysUserService.getCurrentUser().getId())
                .andClubIdEqualTo(clubId);
        List<CmsUserClubRel> userClubRelList = cmsUserClubRelMapper.selectByExample(example);
        int state = isCheckin(clubId,date,userClubRelList);
        if (state == CheckinStateEnum.DENIED.getValue()) {
            Asserts.fail(" 该社团已不存在或您已退出（还未加入）该社团 ");
        }
        if (state == CheckinStateEnum.DONE.getValue()) {
            Asserts.forbidden("今天您已签到过，同一天不可重复签到");
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
            return 2;
        }
        CmsUserClubRel userClubRel = userClubRelList.get(0);
        Date lastCheckinDate = userClubRel.getCheckInDate();
        //从来没签到过
        if (lastCheckinDate == null) {
            return 1;
        }
        //今天已经签过到
        if (DateUtil.isSameDay(date, lastCheckinDate)) {
            return 0;
        }

        return 1;
    }

    @Override
    public void comment(Integer postId) {
        FmsPostRemarkExample example = new FmsPostRemarkExample();
        example.createCriteria().andPostIdEqualTo(postId)
                .andUserIdEqualTo(sysUserService.getCurrentUser().getId());
        List<FmsPostRemark> remarkList = fmsPostRemarkMapper.selectByExample(example);
        //没有评论或者同一帖子评论超过两条不增加积分
        if (CollectionUtils.isEmpty(remarkList)||remarkList.size() >= maxCommentCreditNum){
            System.out.println("没有评论或者同一帖子评论超过两条不增加积分");
            return;
        }
        FmsPost post = fmsPostMapper.selectByPrimaryKey(postId.longValue());
        //个人贴不加积分
        if(post.getType().equals(PostTypeEnum.PERSONAL.getValue())){
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
}
