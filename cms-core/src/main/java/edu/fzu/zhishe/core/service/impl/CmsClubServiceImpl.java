package edu.fzu.zhishe.core.service.impl;

import cn.hutool.json.JSONObject;
import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.*;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.constant.ActivityStateEnum;
import edu.fzu.zhishe.core.constant.ApplyStateEnum;
import edu.fzu.zhishe.core.constant.ClubOfficialStateEnum;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dao.*;


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
    private CmsClubDisbandDAO cmsClubDisbandDAO;

    @Autowired
    CmsClubJoinApplyMapper clubJoinApplyMapper;

    @Autowired
    private CmsClubJoinDAO cmsClubJoinDAO;

    @Autowired
    CmsClubMapper clubMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    CmsUserClubRelMapper userClubRelMapper;

    @Autowired
    CmsQuitNoticeMapper quitNoticeMapper;

    @Autowired
    private CmsClubQuitDAO cmsClubQuitDAO;

    @Autowired
    CmsChiefChangeApplyMapper chiefChangeApplyMapper;

    @Autowired
    private CmsClubChiefChangeDAO cmsClubChiefChangeDAO;

    @Autowired
    CmsOfficialChangeApplyMapper officialChangeApplyMapper;

    @Autowired
    CmsClubCertificationDAO cmsClubCertificationDAO;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    private CmsClubDAO clubDAO;

    @Autowired
    private CmsActivityMapper activityMapper;

    @Autowired
    CmsMemberHonorMapper honorMapper;

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public boolean isClubMember(Integer clubId) {
        ClubStatueEnum clubStatue = getClubStatue(clubId);
        return clubStatue == ClubStatueEnum.CHIEF || clubStatue == ClubStatueEnum.MEMBER;
    }

    @Override
    public ClubStatueEnum getClubStatue(Integer clubId) {
        // 找不到社团
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        if (club == null) {
            return ClubStatueEnum.NONE;
        }

        SysUser currentUser = sysUserService.getCurrentUser();
        Integer userId = currentUser.getId();
        if (club.getChiefId().equals(userId)) {
            return ClubStatueEnum.CHIEF;
        }

        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria()
            .andClubIdEqualTo(clubId)
            .andUserIdEqualTo(userId);
        List<CmsUserClubRel> userClubRels = userClubRelMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userClubRels)) {
            return ClubStatueEnum.NONE;
        }

        return ClubStatueEnum.MEMBER;
    }

    //一下三个函数用于社团创建
    @Override
    public CmsClubCreateApply createClub(CmsClubsCreationsParam clubsCreationsParam) {
        //查询是否已存在该社团
        CmsClubExample example2 = new CmsClubExample();
        example2.createCriteria().andNameEqualTo(clubsCreationsParam.getClubName())
                .andDeleteStatusEqualTo(DeleteStateEnum.Existence.getValue());
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

        cmsClubCreateApply.setApplicant(sysUser.getUsername());
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
    public List<CmsClubsCreationsDTO> listClubCreationApply(CmsClubsCreationsQueryParam cmsClubsCreationsQueryParam,QueryParam queryParam) {
        if(sysUserService.getCurrentUser().getIsAdmin()==0){
            Asserts.fail(" 您不是社团管理员无权查看 ");
        }
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        return cmsClubCreationDAO.listClubCreationApply(cmsClubsCreationsQueryParam);
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
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(clubsDisbandParam.getClubId());
        //查询是否已存在该社团
        if (cmsClub == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        //查询是否已解散
        if(cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()){
            Asserts.fail(" 该社团已解散 ");
        }
        //查询是否是社长
        if (!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())){
            Asserts.fail(" 您不是社长无权解散 ");
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
    public List<CmsClubsDisbandDTO> listClubDisbandApply(CmsClubsDisbandQueryParam cmsClubsDisbandQueryParam,QueryParam queryParam) {
        if(sysUserService.getCurrentUser().getIsAdmin()==0){
            Asserts.fail(" 您不是社团管理员无权查看 ");
        }
        PageHelper.startPage(queryParam.getPage(),queryParam.getLimit());
        List<CmsClubsDisbandDTO> cmsClubsDisbandDTOList = cmsClubDisbandDAO.listClubDisbandApply(cmsClubsDisbandQueryParam);
        return cmsClubsDisbandDTOList;
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

            //删除相关quitNotice记录
            CmsQuitNoticeExample example2 = new CmsQuitNoticeExample();
            example2.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            quitNoticeMapper.deleteByExample(example2);

            //删除相关社团换届记录
            CmsChiefChangeApplyExample example3 = new CmsChiefChangeApplyExample();
            example3.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            chiefChangeApplyMapper.deleteByExample(example3);

            //删除相关社团认证记录
            CmsOfficialChangeApplyExample example4 = new CmsOfficialChangeApplyExample();
            example4.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            officialChangeApplyMapper.deleteByExample(example4);

            //更新相关disbandApply表记录
            cmsClubDisbandApply.setState(ApplyStateEnum.ACTIVE.getValue());
            cmsClubDisbandApply.setHandleAt(new Date());
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
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsJoinParam.getClubId());
        //System.out.println(cmsClub.getDeleteStatus());
        if (cmsClub == null||cmsClub.getDeleteStatus()== DeleteStateEnum.Deleted.getValue()) {
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
    public List<CmsClubsJoinDTO> listJoinClubApply(Integer clubId, CmsClubsJoinQueryParam cmsClubsJoinQueryParam,QueryParam queryParam) {
        // 查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(clubId);
        if ( cmsClub == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        if(!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())){
            Asserts.fail(" 您不是该社团社长无权查看 ");
        }
        PageHelper.startPage(queryParam.getPage(),queryParam.getLimit());
        List<CmsClubsJoinDTO> cmsClubsJoinDTOList = cmsClubJoinDAO.listClubJoinApply(cmsClubsJoinQueryParam,clubId);
        return cmsClubsJoinDTOList;
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
        //增加社长判定，只有社长才能审核加入申请
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubJoinApply.getClubId());
        if(!sysUserService.getCurrentUser().getId().equals(cmsClub.getChiefId())){
            Asserts.fail(" 您不是该社社长无权进行加入申请审核 ");
        }
        if (cmsClubJoinApply.getState() != ApplyStateEnum.PENDING.getValue()) {
            Asserts.fail(" 该申请已经审核完毕 ");
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsClubJoinApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //更新社团人数

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
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsQuitParam.getClubId());
        if ( cmsClub == null||cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()) {
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
        cmsQuitNotice.setQuitDate(new Date());
        cmsQuitNotice.setReason(cmsClubsQuitParam.getReason());
        quitNoticeMapper.insert(cmsQuitNotice);
        //删除user_club表相关记录
        userClubRelMapper.deleteByExample(example);
        //更新club表相关记录人数

        cmsClub.setMemberCount(cmsClub.getMemberCount()-1);
        clubMapper.updateByPrimaryKeySelective(cmsClub);
        return cmsQuitNotice;
    }

    @Override
    public List<CmsClubsQuitDTO> listClubQuit(Integer clubId,CmsClubsQuitQueryParam cmsClubsQuitQueryParam,QueryParam queryParam) {
        // 查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(clubId);
        if ( cmsClub == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        if(!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())){
            Asserts.fail(" 您不是该社团社长无权查看 ");
        }
        PageHelper.startPage(queryParam.getPage(),queryParam.getLimit());
        List<CmsClubsQuitDTO> cmsClubsQuitDTOList = cmsClubQuitDAO.listClubQuit(cmsClubsQuitQueryParam,clubId);
        return cmsClubsQuitDTOList;
    }

    //以下三个函数用于社长换届
    @Override
    public CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam) {
        //虽然感觉多余但是还是验证一下社团是否存在
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsChiefChangeParam.getClubId());
        if (cmsClub == null||cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()) {
            Asserts.fail(" 该社团不存在 ");
        }
        //验证是否是社长提出的解散
        if(!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())){
            Asserts.fail(" 你不是该社团社长无权换届 ");
        }
        //验证新社长是否存在
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(cmsClubsChiefChangeParam.getNewChiefName());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(sysUsers)){
            Asserts.fail(" 该新社长"+ cmsClubsChiefChangeParam.getNewChiefName()+ "用户不存在 ");
        }
        SysUser newChief = sysUsers.get(0);
        //验证新社长是否是社员
        CmsUserClubRelExample example1 = new CmsUserClubRelExample();
        example1.createCriteria().andClubIdEqualTo(cmsClubsChiefChangeParam.getClubId()).andUserIdEqualTo(newChief.getId());
        List<CmsUserClubRel> userClubRels = userClubRelMapper.selectByExample(example1);
        if (CollectionUtils.isEmpty(userClubRels)){
            Asserts.fail(" 该新社长"+ cmsClubsChiefChangeParam.getNewChiefName()+ "不是社团成员 ");
        }
        //验证是否存在PENDING状态的申请
        CmsChiefChangeApplyExample example2 = new CmsChiefChangeApplyExample();
        example2.createCriteria().andClubIdEqualTo(cmsClubsChiefChangeParam.getClubId())
                .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsChiefChangeApply> cmsChiefChangeApplies = chiefChangeApplyMapper
                .selectByExample(example2);
        if (!CollectionUtils.isEmpty(cmsChiefChangeApplies)) {
            Asserts.fail(" 该社团已经申请换届，请等待审核 ");
        }




        //形成申请
//        SysUserExample example3 = new SysUserExample();
//        example3.createCriteria().andUsernameEqualTo(cmsClubsChiefChangeParam.getNewChiefName());

        CmsChiefChangeApply cmsChiefChangeApply = new CmsChiefChangeApply();
        cmsChiefChangeApply.setClubId(cmsClubsChiefChangeParam.getClubId());
        cmsChiefChangeApply.setOldChiefId(sysUserService.getCurrentUser().getId());
        cmsChiefChangeApply.setNewChiefId(newChief.getId());
        cmsChiefChangeApply.setReason(cmsClubsChiefChangeParam.getReason());
        cmsChiefChangeApply.setCreateAt(new Date());
        cmsChiefChangeApply.setHandleAt(null);
        cmsChiefChangeApply.setState(ApplyStateEnum.PENDING.getValue());
        chiefChangeApplyMapper.insert(cmsChiefChangeApply);
        return cmsChiefChangeApply;
    }

    @Override
    public List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(CmsClubsChiefChangeQueryParam cmsClubsChiefChangeQueryParam,QueryParam queryParam) {
        if(sysUserService.getCurrentUser().getIsAdmin()==0){
            Asserts.fail(" 您不是社团管理员无权查看 ");
        }
        PageHelper.startPage(queryParam.getPage(),queryParam.getLimit());
        List<CmsClubsChiefChangeDTO> cmsClubsChiefChangeDTOList = cmsClubChiefChangeDAO.listClubChiefChangeApply(cmsClubsChiefChangeQueryParam);
        return cmsClubsChiefChangeDTOList;
    }

    @Override
    public CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsChiefChangeApply cmsChiefChangeApply = chiefChangeApplyMapper
                .selectByPrimaryKey(cmsClubsAuditParam.getId());
        if (cmsChiefChangeApply == null) {
            Asserts.fail(" 该社团换届申请不存在 ");
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

            //更新申请记录
            cmsChiefChangeApply.setState(ApplyStateEnum.ACTIVE.getValue());
            cmsChiefChangeApply.setHandleAt(new Date());
            chiefChangeApplyMapper.updateByPrimaryKeySelective(cmsChiefChangeApply);

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

    //以下三个用于社团认证
    @Override
    public CmsOfficialChangeApply clubOfficialChange(CmsClubsCertificationsParam cmsClubsCertificationsParam) {
        //查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsCertificationsParam.getClubId());
        if(cmsClub == null||cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()){
            Asserts.fail(" 该社团不存在 ");
        }
        //验证是否是社长提出的认证
        if(!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())){
            Asserts.fail(" 你不是该社团社长无权认证 ");
        }
        //查询该社团是否是非认证社团
        if(cmsClub.getOfficialState() == ClubOfficialStateEnum.OFFICIAL.getValue()){
            Asserts.fail(" 该社团已经是认证社团 ");
        }
        // 查询是否已申请认证该社团
        CmsOfficialChangeApplyExample example = new CmsOfficialChangeApplyExample();
        example.createCriteria().andClubIdEqualTo(cmsClubsCertificationsParam.getClubId())
                .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsOfficialChangeApply> cmsOfficialChangeApplies = officialChangeApplyMapper
                .selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsOfficialChangeApplies)) {
            Asserts.fail(" 该社团已经申请认证，请等待审核 ");
        }

        CmsOfficialChangeApply cmsOfficialChangeApply = new CmsOfficialChangeApply();
        cmsOfficialChangeApply.setClubId(cmsClubsCertificationsParam.getClubId());
        cmsOfficialChangeApply.setReason(cmsClubsCertificationsParam.getReason());
        cmsOfficialChangeApply.setCreateAt(new Date());
        cmsOfficialChangeApply.setHandleAt(null);
        cmsOfficialChangeApply.setState(ApplyStateEnum.PENDING.getValue());
        officialChangeApplyMapper.insert(cmsOfficialChangeApply);
        return cmsOfficialChangeApply;
    }

    @Override
    public List<CmsClubsCertificationsDTO> listClubOfficialChange(CmsClubsCertificationsQueryParam cmsClubsCertificationsQueryParam,QueryParam queryParam) {
        if(sysUserService.getCurrentUser().getIsAdmin()==0){
            Asserts.fail(" 您不是社团管理员无权查看 ");
        }
        PageHelper.startPage(queryParam.getPage(),queryParam.getLimit());
        List<CmsClubsCertificationsDTO> cmsClubsCertificationsDTOList = cmsClubCertificationDAO.listClubCertificationApply(cmsClubsCertificationsQueryParam);
        return cmsClubsCertificationsDTOList;
    }

    @Override
    public CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsOfficialChangeApply cmsOfficialChangeApply = officialChangeApplyMapper
                .selectByPrimaryKey(cmsClubsAuditParam.getId());
        if (cmsOfficialChangeApply == null) {
            Asserts.fail(" 该社团认证申请不存在 ");
        }
        if (!ApplyStateEnum.isLegal(cmsClubsAuditParam.getState())) {
            Asserts.fail(" 该申请状态码不正确 ");
        }
        if (cmsOfficialChangeApply.getState() != ApplyStateEnum.PENDING.getValue()) {
            Asserts.fail(" 该申请已经审核完毕 ");
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsOfficialChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //更新社团官方状态
            CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsOfficialChangeApply.getClubId());
            cmsClub.setOfficialState(ClubOfficialStateEnum.OFFICIAL.getValue());
            clubMapper.updateByPrimaryKeySelective(cmsClub);

            //更新申请记录
            cmsOfficialChangeApply.setState(ApplyStateEnum.ACTIVE.getValue());
            cmsOfficialChangeApply.setHandleAt(new Date());
            officialChangeApplyMapper.updateByPrimaryKeySelective(cmsOfficialChangeApply);

            return cmsOfficialChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            cmsOfficialChangeApply.setState(ApplyStateEnum.REJECTED.getValue());
            cmsOfficialChangeApply.setHandleAt(new Date());
            officialChangeApplyMapper.updateByPrimaryKeySelective(cmsOfficialChangeApply);
            return cmsOfficialChangeApply;
        }
        return cmsOfficialChangeApply;
    }

    @Override
    public List<CmsClubReturnData1> listHotClub(Integer page, Integer limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        return clubDAO.listHotClub(page, limit, sort, order);
    }

    @Override
    public List<CmsClubReturnData1> listClub(Integer page, Integer limit, String sort, String order, String keyword) {
        PageHelper.startPage(page, limit);
        return clubDAO.listClub(sort, order, keyword);
    }

    @Override
    public CmsClubReturnData2 getClubById(Integer id) {
            CmsClub club = clubMapper.selectByPrimaryKey(id);
            SysUser user = sysUserMapper.selectByPrimaryKey(club.getChiefId());
            CmsClubReturnData2 data = new CmsClubReturnData2();
            if(!club.getId().equals(null)) {
                data.setId(club.getId());
                data.setName(club.getName());
                data.setChiefName(user.getNickname());
                data.setAvatarUrl(club.getAvatarUrl());
                data.setSlogan(club.getSlogan());
                data.setType(club.getType());
                data.setMemberCount(club.getMemberCount());
                data.setQqGroup(club.getQqGroup());
            }
            return data ;
    }

    @Override
    public List<CmsClubReturnData1> listJoinedClub(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.listJoinedClub(page,limit,sort,order, userId);
    }

    @Override
    public List<CmsClubReturnData1> listManagedClub(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.listManagedClub(page,limit,sort,order, userId);
    }

    @Override
    public List<CmsClubReturnData3> listJoinClubApply(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.listJoinClubApply(page,limit,sort,order, userId);
    }

    @Override
    public List<CmsClubReturnData4> listCreateClubApply(Integer page, Integer limit, String sort, String order, Integer userId) {
        PageHelper.startPage(page, limit);
        return clubDAO.listCreateClubApply(page,limit,sort,order, userId);
    }

    @Override
    public List<CmsClubReturnData5> listClubMember(Integer page, Integer limit, String sort, String order, Integer clubId) {

        SysUser user = getCurrentUser();
        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(user.getId());
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);
        if (CollectionUtils.isEmpty(userClubList)) {
            Asserts.fail("非社团成员，您没有该权限");
        }

        List<CmsClubReturnData5> dataList = new LinkedList<>();
        userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId);
        userClubList = userClubRelMapper.selectByExample(userClubRel);

        for(CmsUserClubRel rel : userClubList)
        {
            CmsClubReturnData5 data = new CmsClubReturnData5();
            SysUser u = sysUserMapper.selectByPrimaryKey(rel.getUserId());
            BeanUtils.copyProperties(u, data);
            data.setUserId(u.getId());
            CmsMemberHonor honor = honorMapper.selectByPrimaryKey(rel.getHonorId());
            data.setHonor(honor.getName());
            SysRole role = roleMapper.selectByPrimaryKey(rel.getRoleId());
            data.setRole(role.getDescription());
            data.setCredit(rel.getCredit());
            dataList.add(data);
        }
        PageHelper.startPage(page, limit);
        return dataList;
    }

    @Override
    public CmsClubReturnData6 showClubMemberInfo(Integer clubId, Integer userId) {
        //先判断社团里面是否有这个成员

        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId);
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);

        if (CollectionUtils.isEmpty(userClubList)) {
            Asserts.fail("非社团成员，您没有该权限");
        }

        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        CmsClubReturnData6 data = new CmsClubReturnData6();
        //将用户字段和data字段相同的，复制到data里面
        BeanUtils.copyProperties(user, data);

        CmsUserClubRel userClub = userClubList.get(0);

        CmsMemberHonor honor = honorMapper.selectByPrimaryKey(userClub.getHonorId());
        data.setHonor(honor.getName());
        SysRole role = roleMapper.selectByPrimaryKey(userClub.getRoleId());
        data.setRole(role.getDescription());
        data.setCredit(userClub.getCredit());
        return data;
    }

    @Override
    public Integer addClubMember(Integer clubId, Integer userId){
        CmsUserClubRel clubRel = new CmsUserClubRel();
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        Date date = new Date();
        clubRel.setClubId(clubId);
        clubRel.setUserId(userId);
        clubRel.setCredit(0);
        clubRel.setHonorId(1);
        clubRel.setRoleId(2);
        clubRel.setJoinDate(date);
        userClubRelMapper.insert(clubRel);
        club.setMemberCount(club.getMemberCount()+1);
        clubMapper.updateByPrimaryKey(club);
        return 1;
    }

    @Override
    public Integer deleteClubMember(Integer clubId, Integer userId){

        SysUser user = getCurrentUser();
        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(user.getId()).andRoleIdEqualTo(3);
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);
        if (CollectionUtils.isEmpty(userClubList)) {
            Asserts.fail("非社长，您没有该权限");
        }

        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId);
        userClubRelMapper.deleteByExample(userClubRel);
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setMemberCount(club.getMemberCount()-1);
        clubMapper.updateByPrimaryKey(club);
        return 1;
    }

    //修改社团信息接口

    public Integer alterClubSlogan(Integer clubId, Integer userId, String slogan){
        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId).andRoleIdEqualTo(3);
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);
        if (CollectionUtils.isEmpty(userClubList)) {
            Asserts.fail("非社长，您没有该权限");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setSlogan(slogan);
        clubMapper.updateByPrimaryKey(club);
        return 1;
    }

    public Integer alterClubQqGroup(Integer clubId, Integer userId, String qqGroup){
        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId).andRoleIdEqualTo(3);
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);
        if (CollectionUtils.isEmpty(userClubList)) {
            Asserts.fail("非社长，您没有该权限");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setQqGroup(qqGroup);
        clubMapper.updateByPrimaryKey(club);
        return 1;
    }

    public Integer alterClubType(Integer clubId, Integer userId, String type){
        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId).andRoleIdEqualTo(3);
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);
        if (CollectionUtils.isEmpty(userClubList)) {
            Asserts.fail("非社长，您没有该权限");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setType(type);
        clubMapper.updateByPrimaryKey(club);
        return 1;
    }

    public Integer alterClubAvatarUrl(Integer clubId, Integer userId, String avatarUrl){
        CmsUserClubRelExample userClubRel = new CmsUserClubRelExample();
        userClubRel.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId).andRoleIdEqualTo(3);
        List<CmsUserClubRel> userClubList = userClubRelMapper.selectByExample(userClubRel);
        if (CollectionUtils.isEmpty(userClubList)) {
            Asserts.fail("非社长，您没有该权限");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        club.setAvatarUrl(avatarUrl);
        clubMapper.updateByPrimaryKey(club);
        return 1;
    }


    /*
     * @author PSF 2020/04/27
     */

    @Override
    public void activityApply(CmsClubActivityParam param) {
        CmsActivity activity = new CmsActivity();
        BeanUtils.copyProperties(param, activity);
        activity.setStarDate(param.getStartDate());
        activity.setEndData(param.getEndDate());
        activity.setCreateAt(new Date());

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
            activity.setState(ActivityStateEnum.DELETED.getValue());
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

    @Override
    public List<CmsActivityApplyDTO> listActivitiesApply(Integer clubId,
            Integer page, Integer limit, String sort, String order) {
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        SysUser user = getCurrentUser();

        if (club == null) {
            Asserts.fail("社团ID错误，无法获取社团信息");
        }

        if (user == null || !user.getId().equals(club.getChiefId())) {
            Asserts.fail("非社长无法查看申请活动");
        }
        CmsActivityExample example = new CmsActivityExample();
        example.createCriteria().andClubIdEqualTo(clubId);
        example.setOrderByClause(sort + " " + order);

        List<CmsActivityApplyDTO> applyData = new ArrayList<>();
        PageHelper.startPage(page, limit);
        List<CmsActivity> activities = activityMapper.selectByExample(example);

        for (CmsActivity ac : activities) {
            CmsActivityApplyDTO dto = new CmsActivityApplyDTO();
            BeanUtils.copyProperties(ac, dto);
            dto.setContent(ac.getBody());
            dto.setEndDate(ac.getEndData());
            dto.setStartDate(ac.getStarDate());
            applyData.add(dto);
        }
        return applyData;
    }

    @Override
    public CmsActivity getActivityApplyItem(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (activity == null) {
            Asserts.fail("申请ID错误，获取申请活动失败");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        SysUser user = getCurrentUser();
        if (user == null || !user.getId().equals(club.getChiefId())) {
            Asserts.fail("非社长无法查看申请活动");
        }

        return activity;
    }

    @Override
    public void updateActivity(Integer id, CmsActivityUpdateParam param) {
        SysUser user = getCurrentUser();
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (user == null) {
            Asserts.fail("请登录");
        }
        if (activity == null) {
            Asserts.fail("获取活动失败");
        }
        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (!club.getChiefId().equals(user.getId())) {
            Asserts.fail("非社长不能修改活动");
        }
        activity.setName(param.getName());
        activity.setTitle(param.getTitle());
        activity.setBody(param.getContent());
        activity.setStarDate(param.getStarDate());
        activity.setEndData(param.getEndDate());
        if (param.getLocation() != null) {
            activity.setLocation(param.getLocation());
        }
        if (activityMapper.updateByPrimaryKey(activity) == 0) {
            Asserts.fail("更新失败");
        }
    }

    @Override
    public List<CmsAtivityApplyListDTO> listActivitiesApply(CmsActivitySearchParam param,
            Integer page, Integer limit, String sort, String order) {
        SysUser user = getCurrentUser();
        if (user == null || user.getIsAdmin() == 0) {
            Asserts.fail("非管理员无法查询");
        }

        CmsActivityExample acExample = new CmsActivityExample();
        // 进行模糊查询社团名字
        if (param.getClubName() != null) {
            CmsClubExample cce = new CmsClubExample();
            cce.createCriteria().andNameLike("%" + param.getClubName() + "%");
            List<CmsClub> clubs = clubMapper.selectByExample(cce);
            if (clubs.isEmpty()) {
                Asserts.fail("查找不到该社团名字，请重新查找");
            }
            List<Integer> clubIds = new ArrayList<>();
            for (CmsClub club : clubs) {
                clubIds.add(club.getId());
            }
            acExample.createCriteria().andClubIdIn(clubIds);
        }
        if (param.getState() != null) {
            acExample.createCriteria().andStateEqualTo(param.getState());
        }
        acExample.setOrderByClause(sort + " " + order);


        List<CmsAtivityApplyListDTO> list = new ArrayList<>();

        PageHelper.startPage(page, limit);
        List<CmsActivity> activity = activityMapper.selectByExample(acExample);
        for (CmsActivity ac : activity) {
            CmsAtivityApplyListDTO dto = new CmsAtivityApplyListDTO();
            BeanUtils.copyProperties(ac, dto);

            dto.setContent(ac.getBody());
            dto.setStartDate(ac.getStarDate());
            dto.setEndDate(ac.getEndData());
            dto.setClubName(clubMapper.selectByPrimaryKey(ac.getClubId()).getName());
            list.add(dto);
        }

        return list;
    }
}
