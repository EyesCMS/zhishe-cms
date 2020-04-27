package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.*;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.common.util.PageUtil;
import edu.fzu.zhishe.core.constant.ApplyStateEnum;
import edu.fzu.zhishe.core.constant.ClubOfficialStateEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.dao.CmsClubDAO;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.service.CmsClubService;
import edu.fzu.zhishe.core.service.SysUserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 社团管理服务层
 *
 * @author yang
 */

@Service
public class CmsClubServiceImpl implements CmsClubService {

    @Autowired
    CmsClubCreateApplyMapper clubCreateApplyMapper;

    @Autowired
    CmsClubDisbandApplyMapper clubDisbandApplyMapper;

    @Autowired
    CmsClubJoinApplyMapper clubJoinApplyMapper;

    @Autowired
    CmsClubMapper clubMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    CmsUserClubRelMapper userClubRelMapper;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    private CmsClubDAO clubDAO;

    @Autowired
    private CmsActivityMapper activityMapper;

    //一下三个函数用于社团创建
    @Override
    public CmsClubCreateApply clubCreate(CmsClubsCreationsParam clubsCreationsParam) {
        //查询是否已存在该社团
        CmsClubExample example2 = new CmsClubExample();
        example2.createCriteria().andNameEqualTo(clubsCreationsParam.getClubName());
        List<CmsClub> cmsClubs = clubMapper.selectByExample(example2);
        if (!CollectionUtils.isEmpty(cmsClubs)) {
            Asserts.fail(" 该社团已经存在 ");
        }
        // 查询是否已申请创建该社团
        CmsClubCreateApplyExample example1 = new CmsClubCreateApplyExample();
        example1.createCriteria().andClubNameEqualTo(clubsCreationsParam.getClubName())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsClubCreateApply> cmsClubCreateApplies = clubCreateApplyMapper
            .selectByExample(example1);
        if (!CollectionUtils.isEmpty(cmsClubCreateApplies)) {
            Asserts.fail(" 该社团已经申请创建，请等待审核 ");
        }

        //SysUserService sysUserService = new SysUserServiceImpl();
        SysUser sysUser = sysUserService.getCurrentUser();
        CmsClubCreateApply cmsClubCreateApply = new CmsClubCreateApply();

        cmsClubCreateApply.setApplicant(clubsCreationsParam.getApplicant());
        cmsClubCreateApply.setClubName(clubsCreationsParam.getClubName());
        cmsClubCreateApply.setType(clubsCreationsParam.getType());
        cmsClubCreateApply.setOfficialState(clubsCreationsParam.isOfficialState());
        cmsClubCreateApply.setReason(clubsCreationsParam.getReason());
        cmsClubCreateApply.setCreateAt(new Date());
        cmsClubCreateApply.setHandleAt(null);
        cmsClubCreateApply.setState(ApplyStateEnum.PENDING.getValue());
        cmsClubCreateApply.setUserId(sysUser.getId());
        //System.out.println(cmsClubCreateApply.getCreateAt());
        clubCreateApplyMapper.insert(cmsClubCreateApply);
        return cmsClubCreateApply;
    }

    @Override
    public CommonList getClubCreateList(Integer page, Integer limit, String sort, String order) {
        int totalCount = clubCreateApplyMapper.selectByExample(null).size();
        PageHelper.startPage(page, limit);
        return CommonList.getCommonList(clubCreateApplyMapper.selectByExample(null), totalCount);
    }

    @Override
    public CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        // 查询是否已有该社团申请
        CmsClubCreateApply cmsClubCreateApply = clubCreateApplyMapper
            .selectByPrimaryKey(cmsClubsAuditParam.getId());
        if (cmsClubCreateApply == null) {
            Asserts.fail(" 该社团创建申请不存在 ");
        }
        if (!ApplyStateEnum.isLegal(cmsClubsAuditParam.getState())) {
            Asserts.fail(" 该申请状态码不正确 ");
        }
        if (cmsClubCreateApply.getState() != ApplyStateEnum.PENDING.getValue()) {
            Asserts.fail(" 该申请已经审核完毕 ");
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsClubCreateApply;
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //创建社团
            CmsClub cmsClub = new CmsClub();

            cmsClub.setName(cmsClubCreateApply.getClubName());
            cmsClub.setChiefId(cmsClubCreateApply.getUserId());
            cmsClub.setMemberCount(1);
            cmsClub.setOfficialState(cmsClubCreateApply.getOfficialState() ?
                ClubOfficialStateEnum.OFFICIAL.getValue()
                : ClubOfficialStateEnum.UNOFFICIAL.getValue());
            cmsClub.setCreateAt(new Date());
            cmsClub.setDeleteStatus(DeleteStateEnum.Existence.getValue());
            clubMapper.insert(cmsClub);

            //更新申请记录
            Date handleAt = new Date();
            cmsClubCreateApply.setHandleAt(handleAt);
            cmsClubCreateApply.setState(ApplyStateEnum.ACTIVE.getValue());
            clubCreateApplyMapper.updateByPrimaryKeySelective(cmsClubCreateApply);

            //更新club_user表
            CmsUserClubRel cmsUserClubRel = new CmsUserClubRel();
            //查询刚刚插入的社团id
            CmsClubExample example = new CmsClubExample();
            example.createCriteria().andNameEqualTo(cmsClubCreateApply.getClubName());
            List<CmsClub> cmsClubs = clubMapper.selectByExample(example);
            cmsUserClubRel.setClubId(cmsClubs.get(0).getId());
            cmsUserClubRel.setUserId(cmsClubCreateApply.getUserId());
            cmsUserClubRel.setJoinDate(handleAt);
            userClubRelMapper.insert(cmsUserClubRel);

            return cmsClubCreateApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            //更新申请记录
            cmsClubCreateApply.setHandleAt(new Date());
            cmsClubCreateApply.setState(ApplyStateEnum.REJECTED.getValue());
            clubCreateApplyMapper.updateByPrimaryKeySelective(cmsClubCreateApply);
            return cmsClubCreateApply;
        }
        return cmsClubCreateApply;
    }

    //以下三个函数用于社团解散
    @Override
    public CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam) {
        //查询是否已存在该社团
        if (clubMapper.selectByPrimaryKey(clubsDisbandParam.getClubId()) == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        // 查询是否已申请解散该社团
        CmsClubDisbandApplyExample example = new CmsClubDisbandApplyExample();
        example.createCriteria().andClubIdEqualTo(clubsDisbandParam.getClubId())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsClubDisbandApply> cmsClubDisbandApplies = clubDisbandApplyMapper
            .selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsClubDisbandApplies)) {
            Asserts.fail(" 该社团已经申请解散，请等待审核 ");
        }

        CmsClubDisbandApply cmsClubDisbandApply = new CmsClubDisbandApply();

        cmsClubDisbandApply.setClubId(clubsDisbandParam.getClubId());
        cmsClubDisbandApply.setCreateAt(new Date());
        cmsClubDisbandApply.setHandleAt(null);
        cmsClubDisbandApply.setReason(clubsDisbandParam.getReason());
        cmsClubDisbandApply.setState(ApplyStateEnum.PENDING.getValue());

        clubDisbandApplyMapper.insert(cmsClubDisbandApply);
        return cmsClubDisbandApply;
    }

    @Override
    public CommonList getClubDisbandList(Integer page, Integer limit, String sort, String order) {
        int totalCount = clubDisbandApplyMapper.selectByExample(null).size();
        PageHelper.startPage(page, limit);
        return CommonList.getCommonList(clubDisbandApplyMapper.selectByExample(null), totalCount);
    }

    @Override
    public CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsClubDisbandApply cmsClubDisbandApply = clubDisbandApplyMapper
            .selectByPrimaryKey(cmsClubsAuditParam.getId());
        if (cmsClubDisbandApply == null) {
            Asserts.fail(" 该社团解散申请不存在 ");
        }
        if (!ApplyStateEnum.isLegal(cmsClubsAuditParam.getState())) {
            Asserts.fail(" 该申请状态码不正确 ");
        }
        if (cmsClubDisbandApply.getState() != ApplyStateEnum.PENDING.getValue()) {
            Asserts.fail(" 该申请已经审核完毕 ");
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsClubDisbandApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //删除社团
            //删除功能有很多问题，先暂时不删除社团，只删除其他记录

            //删除相关user_club表记录
            CmsUserClubRelExample example = new CmsUserClubRelExample();
            example.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            userClubRelMapper.deleteByExample(example);

            //删除相关joinApply表记录
            CmsClubJoinApplyExample example1 = new CmsClubJoinApplyExample();
            example1.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            clubJoinApplyMapper.deleteByExample(example1);

            //更新相关disbandApply表记录

            cmsClubDisbandApply.setState(ApplyStateEnum.REJECTED.getValue());
            clubDisbandApplyMapper.updateByPrimaryKeySelective(cmsClubDisbandApply);

            //删除社团（逻辑删除）
            CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubDisbandApply.getClubId());
            cmsClub.setDeleteStatus(DeleteStateEnum.Deleted.getValue());
            clubMapper.updateByPrimaryKeySelective(cmsClub);
            //clubMapper.deleteByPrimaryKey(cmsClubDisbandApply.getClubId());

            return cmsClubDisbandApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            //更新申请记录
            cmsClubDisbandApply.setHandleAt(new Date());
            cmsClubDisbandApply.setState(ApplyStateEnum.REJECTED.getValue());
            clubDisbandApplyMapper.updateByPrimaryKeySelective(cmsClubDisbandApply);
            return cmsClubDisbandApply;
        }

        return cmsClubDisbandApply;
    }

    //以下三个函数用于加入社团
    @Override
    public CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam) {
        // 查询是否已存在该社团
        if (clubMapper.selectByPrimaryKey(cmsClubsJoinParam.getClubId()) == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        // 查询是否已经是社团成员
        CmsUserClubRelExample example1 = new CmsUserClubRelExample();
        example1.createCriteria()
            .andClubIdEqualTo(cmsClubsJoinParam.getClubId())
            .andUserIdEqualTo(sysUserService.getCurrentUser().getId());
        List<CmsUserClubRel> cmsUserClubRels = userClubRelMapper.selectByExample(example1);
        if (!CollectionUtils.isEmpty(cmsUserClubRels)) {
            Asserts.fail(" 您已加入该社团 ");
        }
        // 查询是否已申请加入
        CmsClubJoinApplyExample example = new CmsClubJoinApplyExample();
        example.createCriteria()
            .andClubIdEqualTo(cmsClubsJoinParam.getClubId())
            .andUserIdEqualTo(sysUserService.getCurrentUser().getId())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsClubJoinApply> cmsClubJoinApplies = clubJoinApplyMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsClubJoinApplies)) {
            Asserts.fail(" 您已申请加入该社团，请等待审核 ");
        }

        CmsClubJoinApply cmsClubJoinApply = new CmsClubJoinApply();
        BeanUtils.copyProperties(cmsClubsJoinParam, cmsClubJoinApply);
        cmsClubJoinApply.setUserId(sysUserService.getCurrentUser().getId());
        cmsClubJoinApply.setCreateAt(new Date());
        cmsClubJoinApply.setHandleAt(null);
        cmsClubJoinApply.setState(ApplyStateEnum.PENDING.getValue());

        clubJoinApplyMapper.insert(cmsClubJoinApply);
        return cmsClubJoinApply;
    }

    @Override
    public CommonList getClubJoinsList(Integer clubId, Integer page, Integer limit, String sort,
        String order) {
        // 查询是否已存在该社团
        if (clubMapper.selectByPrimaryKey(clubId) == null) {
            Asserts.fail(" 该社团不存在 ");
        }

        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CmsClubJoinApplyExample example = new CmsClubJoinApplyExample();
        example.createCriteria().andClubIdEqualTo(clubId);
        List<CmsClubJoinApply> cmsClubJoinApplies = clubJoinApplyMapper.selectByExample(example);
        List<Map<String, String>> joinMaps = new ArrayList<Map<String, String>>();
        for (CmsClubJoinApply cmsClubJoinApply : cmsClubJoinApplies) {
            Map<String, String> myMap = new HashMap<>();
            myMap.put("applicant", sysUserMapper.selectByPrimaryKey(cmsClubJoinApply.getUserId()).getUsername());
            myMap.put("reason", cmsClubJoinApply.getReason());
            myMap.put("create_at", simpleDateFormat.format(cmsClubJoinApply.getCreateAt()));
            myMap.put("state", ApplyStateEnum.toString(cmsClubJoinApply.getState()));
            joinMaps.add(myMap);
        }
        int totalCount = joinMaps.size();
        return CommonList.getCommonList(PageUtil.startPage(joinMaps, page, limit), totalCount);
    }

    @Override
    public CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsClubJoinApply cmsClubJoinApply = clubJoinApplyMapper
            .selectByPrimaryKey(cmsClubsAuditParam.getId());
        if (cmsClubJoinApply == null) {
            Asserts.fail(" 该社团加入申请不存在 ");
        }
        if (!ApplyStateEnum.isLegal(cmsClubsAuditParam.getState())) {
            Asserts.fail(" 该申请状态码不正确 ");
        }
        if (cmsClubJoinApply.getState() != ApplyStateEnum.PENDING.getValue()) {
            Asserts.fail(" 该申请已经审核完毕 ");
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsClubJoinApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //更新社团人数
            CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubJoinApply.getClubId());
            cmsClub.setMemberCount(cmsClub.getMemberCount() + 1);
            clubMapper.updateByPrimaryKeySelective(cmsClub);
            //更新加入申请
            cmsClubJoinApply.setState(ApplyStateEnum.ACTIVE.getValue());
            Date handleAt = new Date();
            cmsClubJoinApply.setHandleAt(handleAt);
            clubJoinApplyMapper.updateByPrimaryKeySelective(cmsClubJoinApply);
            //更新club_user表
            CmsUserClubRel cmsUserClubRel = new CmsUserClubRel();
            cmsUserClubRel.setUserId(cmsClubJoinApply.getUserId());
            cmsUserClubRel.setClubId(cmsClubJoinApply.getClubId());
            cmsUserClubRel.setJoinDate(handleAt);
            userClubRelMapper.insert(cmsUserClubRel);

            return cmsClubJoinApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            cmsClubJoinApply.setState(ApplyStateEnum.REJECTED.getValue());
            cmsClubJoinApply.setHandleAt(new Date());
            clubJoinApplyMapper.updateByPrimaryKeySelective(cmsClubJoinApply);
            return cmsClubJoinApply;
        }

        return cmsClubJoinApply;
    }


    @Override
    public List<CmsClub> getHotClubList(Integer page, Integer limit) {
        return clubDAO.getHotClubList(page, limit);
    }

    @Override
    public List<CmsClub> getClubList(Integer page, Integer limit) {
        CmsClubExample clubExample = new CmsClubExample();
        clubExample.createCriteria().andDeleteStatusEqualTo(0);
        PageHelper.startPage(page, limit);
        return clubMapper.selectByExample(clubExample);
    }

    /*
     * @author PSF 2020/04/27
     */
    @Override
    public void AtivityApply(CmsClubActivityParam param) {
        CmsActivity activity = new CmsActivity();
        BeanUtils.copyProperties(param, activity);
        SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
        try {
            activity.setStarDate(sDF.parse(param.getStartDate()));
            activity.setEndData(sDF.parse(param.getEndDate()));
            activity.setCreateAt(new Date());
        }
        catch (ParseException e) {
            Asserts.fail("申请时间格式不正确");
        }
        activity.setState(0);
        if (activityMapper.insert(activity) == 0) {
            Asserts.fail("创建申请活动失败");
        }
    }

    @Override
    public void ActivityStateChange(Integer applyId, Integer stateId, String role) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(applyId);
        if (role.equals("sys") && activity.getState() == 0) {
            if (stateId == 1 || stateId == 3) {
                activity.setState(stateId);
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        else if (role.equals("chief") && activity.getState() == 1) {
            if (stateId == 2 || stateId == 4) {
                activity.setState(stateId);
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        Asserts.fail("状态未改变或权限不足");
    }
}
