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
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dao.CmsClubCreationDAO;
import edu.fzu.zhishe.core.dao.CmsClubDAO;
import edu.fzu.zhishe.core.domain.SysUserDetails;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.service.CmsClubService;
import edu.fzu.zhishe.core.service.SysUserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CmsClubCreationDAO cmsClubCreationDAO;

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
    CmsQuitNoticeMapper quitNoticeMapper;

    @Autowired
    CmsChiefChangeApplyMapper chiefChangeApplyMapper;

    @Autowired
    CmsOfficialChangeApplyMapper officialChangeApplyMapper;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    private CmsClubDAO clubDAO;

    @Autowired
    private CmsActivityMapper activityMapper;

    //一下三个函数用于社团创建
    @Override
    public CmsClubCreateApply createClub(CmsClubsCreationsParam clubsCreationsParam) {
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
    public List<CmsClubCreateApply> listClubCreationApply(CmsClubCreationQueryParam queryParam, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        return cmsClubCreationDAO.listClubCreationApply(queryParam);
    }

    // @Override
    public CommonList getClubCreateList(QueryParam queryParam) {
        // TODO: 对照api添加jsonignore
        int totalCount = clubCreateApplyMapper.selectByExample(null).size();
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
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
            cmsClub.setType(cmsClubCreateApply.getType());
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
    public CommonList getClubDisbandList(QueryParam queryParam) {
        // TODO: 对照api添加jsonignore
        int totalCount = clubDisbandApplyMapper.selectByExample(null).size();
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
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
    public CommonList getClubJoinsList(Integer clubId, QueryParam queryParam) {
        // 查询是否已存在该社团
        if (clubMapper.selectByPrimaryKey(clubId) == null) {
            Asserts.fail(" 该社团不存在 ");
        }

        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CmsClubJoinApplyExample example = new CmsClubJoinApplyExample();
        example.createCriteria().andClubIdEqualTo(clubId);
        List<CmsClubJoinApply> cmsClubJoinApplies = clubJoinApplyMapper.selectByExample(example);
        List<Map<String, Object>> joinMaps = new ArrayList<Map<String, Object>>();
        for (CmsClubJoinApply cmsClubJoinApply : cmsClubJoinApplies) {
            Map<String, Object> myMap = new LinkedHashMap<>();
            myMap.put("applicant", sysUserMapper.selectByPrimaryKey(cmsClubJoinApply.getUserId()).getUsername());
            myMap.put("reason", cmsClubJoinApply.getReason());
            myMap.put("create_at", simpleDateFormat.format(cmsClubJoinApply.getCreateAt()));
            myMap.put("state", cmsClubJoinApply.getState());
            joinMaps.add(myMap);
        }
        int totalCount = joinMaps.size();
        return CommonList.getCommonList(PageUtil.startPage(joinMaps, queryParam.getPage(), queryParam.getLimit()), totalCount);
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

    //以下两个函数用于退出社团
    @Override
    public CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam) {
        //由于不用审核，就不需要重复申请判断
        // 查询是否已存在该社团
        if (clubMapper.selectByPrimaryKey(cmsClubsQuitParam.getClubId()) == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        //前端页面社长没有退社按钮就不需要验证了
        //虽然感觉没必要但还是验证一下该用户是否是该社团成员
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria()
                .andClubIdEqualTo(cmsClubsQuitParam.getClubId())
                .andUserIdEqualTo(sysUserService.getCurrentUser().getId());
        List<CmsUserClubRel> cmsUserClubRels = userClubRelMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(cmsUserClubRels)) {
            Asserts.fail(" 您不是该社团成员 ");
        }
        //添加退出记录
        CmsQuitNotice cmsQuitNotice = new CmsQuitNotice();
        cmsQuitNotice.setClubId(cmsClubsQuitParam.getClubId());
        cmsQuitNotice.setUserId(sysUserService.getCurrentUser().getId());
        cmsQuitNotice.setQiutDate(new Date());
        cmsQuitNotice.setReadon(cmsClubsQuitParam.getReason());
        quitNoticeMapper.insert(cmsQuitNotice);
        //删除user_club表相关记录
        userClubRelMapper.deleteByExample(example);
        //更新club表相关记录人数
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsQuitParam.getClubId());
        cmsClub.setMemberCount(cmsClub.getMemberCount()-1);
        clubMapper.updateByPrimaryKeySelective(cmsClub);
        return cmsQuitNotice;
    }

    @Override
    public CommonList getClubQuitList(Integer clubId,QueryParam queryParam) {
        // 查询是否已存在该社团
        if (clubMapper.selectByPrimaryKey(clubId) == null) {
            Asserts.fail(" 该社团不存在 ");
        }

        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        CmsQuitNoticeExample example = new CmsQuitNoticeExample();
        example.createCriteria().andClubIdEqualTo(clubId);
        List<CmsQuitNotice> cmsQuitNotices = quitNoticeMapper.selectByExample(example);
        List<Map<String, Object>> quitMaps = new ArrayList<Map<String, Object>>();
        for (CmsQuitNotice cmsQuitNotice : cmsQuitNotices) {
            Map<String, Object> myMap = new LinkedHashMap<>();
            myMap.put("applicant", sysUserMapper.selectByPrimaryKey(cmsQuitNotice.getUserId()).getUsername());
            myMap.put("reason", cmsQuitNotice.getReadon());
            myMap.put("create_at", simpleDateFormat.format(cmsQuitNotice.getQiutDate()));
            quitMaps.add(myMap);
        }
        int totalCount = quitMaps.size();
        return CommonList.getCommonList(PageUtil.startPage(quitMaps, queryParam.getPage(), queryParam.getLimit()), totalCount);
    }

    //以下三个函数用于社长换届
    @Override
    public CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam) {
        //虽然感觉多余但是还是验证一下社团是否存在
        if (clubMapper.selectByPrimaryKey(cmsClubsChiefChangeParam.getClubId()) == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        //验证是否存在PENDING状态的申请
        CmsChiefChangeApplyExample example = new CmsChiefChangeApplyExample();
        example.createCriteria().andClubIdEqualTo(cmsClubsChiefChangeParam.getClubId())
                .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsChiefChangeApply> cmsChiefChangeApplies = chiefChangeApplyMapper
                .selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsChiefChangeApplies)) {
            Asserts.fail(" 该社团已经申请换届，请等待审核 ");
        }
        //是不是要多验证一部有无新社长这个人？以及验证新社长是否是该社团成员？
        //验证旧社长是否是这个社团的社长？这些验证如果前端在新社长选择用的是下拉框可能就不需要了


        //形成申请
        SysUserExample example1 = new SysUserExample();
        example1.createCriteria().andUsernameEqualTo(cmsClubsChiefChangeParam.getNewChiefName());
        SysUser newChief = sysUserMapper.selectByExample(example1).get(0);
        CmsChiefChangeApply cmsChiefChangeApply = new CmsChiefChangeApply();
        cmsChiefChangeApply.setClubId(cmsClubsChiefChangeParam.getClubId());
        cmsChiefChangeApply.setOldChiefId(cmsClubsChiefChangeParam.getOldChiefId());
        cmsChiefChangeApply.setNewChiefId(newChief.getId());
        cmsChiefChangeApply.setReason(cmsClubsChiefChangeParam.getReason());
        cmsChiefChangeApply.setCreateAt(new Date());
        cmsChiefChangeApply.setHandleAt(null);
        cmsChiefChangeApply.setState(ApplyStateEnum.PENDING.getValue());
        chiefChangeApplyMapper.insert(cmsChiefChangeApply);
        return cmsChiefChangeApply;
    }

    @Override
    public CommonList getClubChiefChangeList(QueryParam queryParam) {
        //设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<CmsChiefChangeApply> cmsChiefChangeApplies = chiefChangeApplyMapper.selectByExample(null);
        List<Map<String, Object>> chiefChangeMaps = new ArrayList<Map<String, Object>>();
        for (CmsChiefChangeApply cmsChiefChangeApply : cmsChiefChangeApplies) {
            Map<String, Object> myMap = new LinkedHashMap<>();
            myMap.put("id",cmsChiefChangeApply.getId());
            myMap.put("clubName",clubMapper.selectByPrimaryKey(cmsChiefChangeApply.getClubId()).getName());
            myMap.put("odlChiefName", sysUserMapper.selectByPrimaryKey(cmsChiefChangeApply.getOldChiefId()).getUsername());
            myMap.put("newChiefName", sysUserMapper.selectByPrimaryKey(cmsChiefChangeApply.getNewChiefId()).getUsername());
            myMap.put("create_at", simpleDateFormat.format(cmsChiefChangeApply.getCreateAt()));
            myMap.put("state", cmsChiefChangeApply.getState());
            chiefChangeMaps.add(myMap);
        }
        int totalCount = chiefChangeMaps.size();
        return CommonList.getCommonList(PageUtil.startPage(chiefChangeMaps, queryParam.getPage(), queryParam.getLimit()), totalCount);
    }

    @Override
    public CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsChiefChangeApply cmsChiefChangeApply = chiefChangeApplyMapper
                .selectByPrimaryKey(cmsClubsAuditParam.getId());
        if (cmsChiefChangeApply == null) {
            Asserts.fail(" 该社团解散申请不存在 ");
        }
        if (!ApplyStateEnum.isLegal(cmsClubsAuditParam.getState())) {
            Asserts.fail(" 该申请状态码不正确 ");
        }
        if (cmsChiefChangeApply.getState() != ApplyStateEnum.PENDING.getValue()) {
            Asserts.fail(" 该申请已经审核完毕 ");
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsChiefChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //更新社团社长
            CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsChiefChangeApply.getClubId());
            cmsClub.setChiefId(cmsChiefChangeApply.getNewChiefId());
            clubMapper.updateByPrimaryKeySelective(cmsClub);
            //老社长要不要退社有待讨论

            return cmsChiefChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            cmsChiefChangeApply.setState(ApplyStateEnum.REJECTED.getValue());
            cmsChiefChangeApply.setHandleAt(new Date());
            chiefChangeApplyMapper.updateByPrimaryKeySelective(cmsChiefChangeApply);
            return cmsChiefChangeApply;
        }
        return cmsChiefChangeApply;
    }

    @Override
    public CmsOfficialChangeApply clubOfficialChange(CmsCertificationsParam certificationsParam) {
        //查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(certificationsParam.getClubId());
        if(cmsClub == null){
            Asserts.fail(" 该社团不存在 ");
        }
        //查询该社团是否是非认证社团
        if(cmsClub.getOfficialState() == ClubOfficialStateEnum.OFFICIAL.getValue()){
            Asserts.fail(" 该社团已经是认证社团 ");
        }
        // 查询是否已申请认证该社团
        CmsOfficialChangeApplyExample example = new CmsOfficialChangeApplyExample();
        example.createCriteria().andClubIdEqualTo(certificationsParam.getClubId())
                .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsOfficialChangeApply> cmsOfficialChangeApplies = officialChangeApplyMapper
                .selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsOfficialChangeApplies)) {
            Asserts.fail(" 该社团已经申请认证，请等待审核 ");
        }

        CmsOfficialChangeApply cmsOfficialChangeApply = new CmsOfficialChangeApply();
        cmsOfficialChangeApply.setClubId(certificationsParam.getClubId());
        cmsOfficialChangeApply.setReason(certificationsParam.getReason());
        cmsOfficialChangeApply.setCreateAt(new Date());
        cmsOfficialChangeApply.setHandleAt(null);
        cmsOfficialChangeApply.setState(ApplyStateEnum.PENDING.getValue());
        officialChangeApplyMapper.insert(cmsOfficialChangeApply);
        return cmsOfficialChangeApply;
    }

    @Override
    public CommonList getClubOfficialChangeList(QueryParam queryParam) {
        // TODO: 等待api修改
//        //设置日期格式
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<CmsOfficialChangeApply> cmsOfficialChangeApplies = officialChangeApplyMapper.selectByExample(null);
//        List<Map<String, Object>> officialChangeMaps = new ArrayList<Map<String, Object>>();
//        for (CmsOfficialChangeApply cmsOfficialChangeApply : cmsOfficialChangeApplies) {
//            Map<String, Object> myMap = new LinkedHashMap<>();
//            myMap.put("id",cmsOfficialChangeApply.getId());
//            myMap.put("clubName",clubMapper.selectByPrimaryKey(cmsOfficialChangeApply.getClubId()).getName());
//            myMap.put("accessoryUrl", cmsOfficialChangeApply.);
//            myMap.put("create_at", simpleDateFormat.format(cmsChiefChangeApply.getCreateAt()));
//            myMap.put("state", ApplyStateEnum.toString(cmsChiefChangeApply.getState()));
//            chiefChangeMaps.add(myMap);
//        }
//        int totalCount = chiefChangeMaps.size();
        return null;
    }

    @Override
    public CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        // TODO: 等待api修改
        return null;
    }


    @Override
    public List<CmsClub> getHotClubList(Integer page, Integer limit) {
        return clubDAO.getHotClubList(page, limit);
    }

    @Override
    public List<CmsClub> getClubList(Integer page, Integer limit,String sort, String order) {
        PageHelper.startPage(page, limit);
        return clubDAO.getClubList(page,limit,sort,order);
    }

    @Override
    public List<CmsClub> searchClubByKeyword(Integer page, Integer limit, String sort, String order, String keyword) {
        PageHelper.startPage(page, limit);
        return clubDAO.searchClubByKeyword(page,limit,sort,order, keyword);
    }

    @Override
    public List<CmsClub> searchClubById (Integer id) {
        return clubDAO.searchClubById(id);
    }

    @Override
    public List<CmsClub> searchJoinedClub(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.searchJoinedClub(page,limit,sort,order, userId);
    }

    @Override
    public List<CmsClub> searchManagedClub(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.searchManagedClub(page,limit,sort,order, userId);
    }

    @Override
    public List<CmsClub> searchJoinedApplyList(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.searchJoinedApplyList(page,limit,sort,order, userId);
    }

    @Override
    public List<CmsClub> searchCreateApplyList(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.searchCreateApplyList(page,limit,sort,order, userId);
    }

    /*
     * @author PSF 2020/04/27
     */

    @Override
    public void ativityApply(CmsClubActivityParam param) {
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
    public void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(applyId);
        if (role.equals(UserRoleEnum.ADMIN) && activity.getState() == 0) {
            if (stateId == 1 || stateId == 3) {
                activity.setState(stateId);
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        else if (role.equals(UserRoleEnum.CHIEF) && activity.getState() == 1) {
            if (stateId == 2 || stateId == 4) {
                activity.setState(stateId);
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        Asserts.fail("状态未改变或权限不足");
    }

    @Override
    public void delActivity(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (activity == null || club == null) {
            Asserts.fail("活动ID不存在");
        }
        SysUser user = getCurrentUser();
        if (user.getIsAdmin() == 1 || user.getId().equals(club.getChiefId())) {
            activity.setState(5);
            if (activityMapper.updateByPrimaryKey(activity) == 0) {
                Asserts.fail("删除失败，删除0行");
            }
        } else {
            Asserts.fail("非社长或管理员，权限不足");
        }
    }

    private SysUser getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth.getPrincipal() == "anonymousUser") {
            return null;
        }
        SysUserDetails userDetails = (SysUserDetails) auth.getPrincipal();
        return userDetails.getSysUser();
    }
}
