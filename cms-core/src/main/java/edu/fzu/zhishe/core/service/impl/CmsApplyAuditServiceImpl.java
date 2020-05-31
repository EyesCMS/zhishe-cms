package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.*;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.CheckClubAuth;
import edu.fzu.zhishe.core.annotation.IsAdmin;
import edu.fzu.zhishe.core.constant.*;
import edu.fzu.zhishe.core.dao.CmsClubCertificationDAO;
import edu.fzu.zhishe.core.dao.CmsClubChiefChangeDAO;
import edu.fzu.zhishe.core.dao.CmsClubCreationDAO;
import edu.fzu.zhishe.core.dao.CmsClubDisbandDAO;
import edu.fzu.zhishe.core.dao.CmsClubJoinDAO;
import edu.fzu.zhishe.core.dao.CmsClubQuitDAO;
import edu.fzu.zhishe.core.dto.CmsClubsCertificationsDTO;
import edu.fzu.zhishe.core.dto.CmsClubsChiefChangeDTO;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsDTO;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandDTO;
import edu.fzu.zhishe.core.dto.CmsClubsJoinDTO;
import edu.fzu.zhishe.core.dto.CmsClubsQuitDTO;
import edu.fzu.zhishe.core.error.ApplyAuditErrorEnum;
import edu.fzu.zhishe.core.param.CmsClubsAuditParam;
import edu.fzu.zhishe.core.param.CmsClubsCertificationsParam;
import edu.fzu.zhishe.core.param.CmsClubsCertificationsQuery;
import edu.fzu.zhishe.core.param.CmsClubsChiefChangeParam;
import edu.fzu.zhishe.core.param.CmsClubsChiefChangeQuery;
import edu.fzu.zhishe.core.param.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.param.CmsClubsCreationsQuery;
import edu.fzu.zhishe.core.param.CmsClubsDisbandParam;
import edu.fzu.zhishe.core.param.CmsClubsDisbandQuery;
import edu.fzu.zhishe.core.param.CmsClubsJoinParam;
import edu.fzu.zhishe.core.param.CmsClubsJoinQuery;
import edu.fzu.zhishe.core.param.CmsClubsQuitParam;
import edu.fzu.zhishe.core.param.CmsClubsQuitQuery;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsApplyAuditService;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.core.util.NotExistUtil;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author yang
 */
@Service
public class CmsApplyAuditServiceImpl implements CmsApplyAuditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsApplyAuditServiceImpl.class);

    @Value("${zhishe.apply.expire_day}")
    private int applyExpireDay;

    @Autowired
    private CmsClubCreationDAO cmsClubCreationDAO;

    @Autowired
    private CmsClubDisbandDAO cmsClubDisbandDAO;

    @Autowired
    private CmsClubJoinDAO cmsClubJoinDAO;

    @Autowired
    private CmsClubQuitDAO cmsClubQuitDAO;

    @Autowired
    private CmsClubChiefChangeDAO cmsClubChiefChangeDAO;

    @Autowired
    private CmsClubMapper clubMapper;

    @Autowired
    private CmsClubCreateApplyMapper clubCreateApplyMapper;

    @Autowired
    private CmsClubDisbandApplyMapper clubDisbandApplyMapper;

    @Autowired
    private CmsClubJoinApplyMapper clubJoinApplyMapper;

    @Autowired
    private CmsQuitNoticeMapper quitNoticeMapper;

    @Autowired
    private CmsChiefChangeApplyMapper chiefChangeApplyMapper;

    @Autowired
    private CmsOfficialChangeApplyMapper officialChangeApplyMapper;

    @Autowired
    private CmsClubCertificationDAO cmsClubCertificationDAO;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private CmsUserClubRelMapper userClubRelMapper;

    @Autowired
    private CmsClubPictureMapper pictureMapper;

    @Autowired
    private CmsActivityMapper activityMapper;

    @Autowired
    private CmsBulletinMapper bulletinMapper;

    @Autowired
    private FmsPostMapper postMapper;

    @Autowired
    private FmsPostRemarkMapper postRemarkMapper;

    @Autowired
    private FmsUserLikePostMapper userLikePostMapper;

    public void checkNotExistOrAudited(Object auditObject) {

        if (auditObject == null) {
            Asserts.notFound(ApplyAuditErrorEnum.APPLY_NOT_EXIST);
        }

        Integer state = ApplyStateEnum.PENDING.getValue();
        if (auditObject instanceof CmsClubCreateApply) {
            // 创建社团
            state = ((CmsClubCreateApply) auditObject).getState();
        } else if (auditObject instanceof CmsClubDisbandApply) {
            // 解散社团
            state = ((CmsClubDisbandApply) auditObject).getState();
        } else if (auditObject instanceof CmsClubJoinApply) {
            // 加入社团
            state = ((CmsClubJoinApply) auditObject).getState();
        } else if (auditObject instanceof CmsChiefChangeApply) {
            // 社长换届
            state = ((CmsChiefChangeApply) auditObject).getState();
        } else if (auditObject instanceof CmsOfficialChangeApply) {
            // 社团认证
            state = ((CmsOfficialChangeApply) auditObject).getState();
        } else {
            Asserts.fail(ApplyAuditErrorEnum.NOT_APPLY);
        }

        if (state != ApplyStateEnum.PENDING.getValue()) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_AUDIT);
        }
    }

    //一下三个函数用于社团创建
    @Override
    public int createClub(CmsClubsCreationsParam clubsCreationsParam) {
        //查询是否已存在该社团
        CmsClubExample example2 = new CmsClubExample();
        example2.createCriteria().andNameEqualTo(clubsCreationsParam.getClubName())
            .andDeleteStatusEqualTo(DeleteStateEnum.Existence.getValue());
        List<CmsClub> cmsClubs = clubMapper.selectByExample(example2);
        if (!CollectionUtils.isEmpty(cmsClubs)) {
            Asserts.fail(ApplyAuditErrorEnum.CLUB_ALREADY_EXIST);
        }
        // 查询是否已申请创建该社团
        CmsClubCreateApplyExample example1 = new CmsClubCreateApplyExample();
        example1.createCriteria().andClubNameEqualTo(clubsCreationsParam.getClubName())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsClubCreateApply> cmsClubCreateApplies = clubCreateApplyMapper
            .selectByExample(example1);
        if (!CollectionUtils.isEmpty(cmsClubCreateApplies)) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_APPLY_CREATE);
        }
        //验证社团类型
        boolean flag = false;
        for (ClubTypeEnum c : ClubTypeEnum.values()) {
            if (c.getMessage().equals(clubsCreationsParam.getType())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            Asserts.notFound(ApplyAuditErrorEnum.CLUB_TYPE_NOT_EXIST);
        }

        //SysUserService sysUserService = new SysUserServiceImpl();
        SysUser sysUser = sysUserService.getCurrentUser();
        CmsClubCreateApply cmsClubCreateApply = new CmsClubCreateApply();

        // clubName, type, officialState, reason
        BeanUtils.copyProperties(clubsCreationsParam, cmsClubCreateApply);
        cmsClubCreateApply.setApplicant(sysUser.getUsername());
        cmsClubCreateApply.setCreateAt(new Date());
        cmsClubCreateApply.setHandleAt(null);
        cmsClubCreateApply.setState(ApplyStateEnum.PENDING.getValue());
        cmsClubCreateApply.setUserId(sysUser.getId());
        //System.out.println(cmsClubCreateApply.getCreateAt());

        return clubCreateApplyMapper.insert(cmsClubCreateApply);
    }

    @Override
    @IsAdmin
    public List<CmsClubsCreationsDTO> listClubCreationApply(
        CmsClubsCreationsQuery cmsClubsCreationsQuery,
        PaginationParam paginationParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return cmsClubCreationDAO.listClubCreationApply(cmsClubsCreationsQuery);
    }

    @Override
    @Transactional
    public CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        // 查询是否已有该社团申请
        CmsClubCreateApply cmsClubCreateApply = clubCreateApplyMapper.selectByPrimaryKey(cmsClubsAuditParam.getId());
        checkNotExistOrAudited(cmsClubCreateApply);

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsClubCreateApply;
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //创建社团
            CmsClub cmsClub = new CmsClub();
            cmsClub.setName(cmsClubCreateApply.getClubName());
            cmsClub.setChiefId(cmsClubCreateApply.getUserId());
            cmsClub.setMemberCount(1);
            cmsClub.setOfficialState(cmsClubCreateApply.getOfficialState());
            cmsClub.setType(cmsClubCreateApply.getType());
            cmsClub.setCreateAt(new Date());
            cmsClub.setCredit(0);
            cmsClub.setGradeId(1);
            cmsClub.setDeleteStatus(DeleteStateEnum.Existence.getValue());
            if(clubMapper.insert(cmsClub) == 0){
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            //更新申请记录
            Date handleAt = new Date();
            cmsClubCreateApply.setHandleAt(handleAt);
            cmsClubCreateApply.setState(ApplyStateEnum.ACTIVE.getValue());
            if (clubCreateApplyMapper.updateByPrimaryKeySelective(cmsClubCreateApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            //更新club_user表
            CmsUserClubRel cmsUserClubRel = new CmsUserClubRel();
            //查询刚刚插入的社团id
            CmsClubExample example = new CmsClubExample();
            example.createCriteria().andNameEqualTo(cmsClubCreateApply.getClubName())
                    .andDeleteStatusEqualTo(DeleteStateEnum.Existence.getValue());
            List<CmsClub> cmsClubs = clubMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(cmsClubs)) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            cmsUserClubRel.setClubId(cmsClubs.get(0).getId());
            cmsUserClubRel.setUserId(cmsClubCreateApply.getUserId());
            cmsUserClubRel.setRoleId(UserRoleEnum.CHIEF.getValue());
            cmsUserClubRel.setCredit(0);
            cmsUserClubRel.setHonorId(1);
            cmsUserClubRel.setJoinDate(handleAt);
            if (userClubRelMapper.insert(cmsUserClubRel) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            //更新社团走马灯图片表
            CmsClubPicture cmsClubPicture = new CmsClubPicture();
            cmsClubPicture.setClubId(cmsClubs.get(0).getId());
            if (pictureMapper.insert(cmsClubPicture) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            return cmsClubCreateApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            //更新申请记录
            cmsClubCreateApply.setHandleAt(new Date());
            cmsClubCreateApply.setState(ApplyStateEnum.REJECTED.getValue());
            if (clubCreateApplyMapper.updateByPrimaryKeySelective(cmsClubCreateApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
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
            Asserts.notFound(ApplyAuditErrorEnum.CLUB_NOT_EXIST);
        }
        //查询是否已解散
        if (cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()) {
            Asserts.notFound(ApplyAuditErrorEnum.CLUB_ALREADY_DISBAND);
        }
        //查询是否是社长
        if (!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())) {
            Asserts.forbidden(ApplyAuditErrorEnum.CAN_NOT_DISBAND);
        }

        // 查询是否已申请解散该社团
        CmsClubDisbandApplyExample example = new CmsClubDisbandApplyExample();
        example.createCriteria().andClubIdEqualTo(clubsDisbandParam.getClubId())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsClubDisbandApply> cmsClubDisbandApplies = clubDisbandApplyMapper
            .selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsClubDisbandApplies)) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_APPLY_DISBAND);
        }

        CmsClubDisbandApply cmsClubDisbandApply = new CmsClubDisbandApply();

        cmsClubDisbandApply.setClubId(clubsDisbandParam.getClubId());
        cmsClubDisbandApply.setCreateAt(new Date());
        cmsClubDisbandApply.setHandleAt(null);
        cmsClubDisbandApply.setReason(clubsDisbandParam.getReason());
        cmsClubDisbandApply.setState(ApplyStateEnum.PENDING.getValue());

        if (clubDisbandApplyMapper.insert(cmsClubDisbandApply) == 0) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        return cmsClubDisbandApply;
    }

    @Override
    @IsAdmin
    public List<CmsClubsDisbandDTO> listClubDisbandApply(
        CmsClubsDisbandQuery cmsClubsDisbandQuery,
        PaginationParam paginationParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return cmsClubDisbandDAO.listClubDisbandApply(cmsClubsDisbandQuery);
    }

    @Override
    @Transactional
    public CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsClubDisbandApply cmsClubDisbandApply = clubDisbandApplyMapper
            .selectByPrimaryKey(cmsClubsAuditParam.getId());
        checkNotExistOrAudited(cmsClubDisbandApply);

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsClubDisbandApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //删除社团
            //删除功能有很多问题，先暂时不删除社团，只删除其他记录
            //删除相关user_club表记录
            CmsUserClubRelExample example = new CmsUserClubRelExample();
            example.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            if (userClubRelMapper.deleteByExample(example) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

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

            //删除活动
            CmsActivityExample example5 = new CmsActivityExample();
            example5.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            activityMapper.deleteByExample(example5);

            //删除公告
            CmsBulletinExample example6 = new CmsBulletinExample();
            example6.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            bulletinMapper.deleteByExample(example6);

            //删除走马灯
            CmsClubPictureExample example7 = new CmsClubPictureExample();
            example7.createCriteria().andClubIdEqualTo(cmsClubDisbandApply.getClubId());
            pictureMapper.deleteByExample(example7);

            //删除活动帖子
            //获得帖子列表
            FmsPostExample example8 = new FmsPostExample();
            example8.createCriteria().andPosterIdEqualTo(cmsClubDisbandApply.getClubId())
                    .andTypeEqualTo(PostTypeEnum.ACTIVITY.getValue());
            List<FmsPost> postList = postMapper.selectByExample(example8);
            for(FmsPost p : postList){
                Long postId = p.getId();
                //删除评论
                FmsPostRemarkExample example9 = new FmsPostRemarkExample();
                example9.createCriteria().andPostIdEqualTo(postId.intValue());
                postRemarkMapper.deleteByExample(example9);
                //删除点赞
                FmsUserLikePostExample example10 = new FmsUserLikePostExample();
                example10.createCriteria().andPostIdEqualTo(postId);
                userLikePostMapper.deleteByExample(example10);
                //逻辑删除帖子
                p.setDeleteState(DeleteStateEnum.Deleted.getValue());
                postMapper.updateByPrimaryKeySelective(p);
            }

            //更新相关disbandApply表记录
            cmsClubDisbandApply.setState(ApplyStateEnum.ACTIVE.getValue());
            cmsClubDisbandApply.setHandleAt(new Date());
            if (clubDisbandApplyMapper.updateByPrimaryKeySelective(cmsClubDisbandApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            //删除社团（逻辑删除）
            CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubDisbandApply.getClubId());
            cmsClub.setDeleteStatus(DeleteStateEnum.Deleted.getValue());
            if (clubMapper.updateByPrimaryKeySelective(cmsClub) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            return cmsClubDisbandApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            //更新申请记录
            cmsClubDisbandApply.setHandleAt(new Date());
            cmsClubDisbandApply.setState(ApplyStateEnum.REJECTED.getValue());
            if (clubDisbandApplyMapper.updateByPrimaryKeySelective(cmsClubDisbandApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            return cmsClubDisbandApply;
        }
        return cmsClubDisbandApply;
    }

    //以下三个函数用于加入社团
    @Override
    public CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam) {
        // 查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsJoinParam.getClubId());
        if (NotExistUtil.check(cmsClub)) {
            Asserts.notFound(ApplyAuditErrorEnum.CLUB_NOT_EXIST);
        }
        // 查询是否已经是社团成员
        CmsUserClubRelExample example1 = new CmsUserClubRelExample();
        example1.createCriteria()
            .andClubIdEqualTo(cmsClubsJoinParam.getClubId())
            .andUserIdEqualTo(sysUserService.getCurrentUser().getId());
        List<CmsUserClubRel> cmsUserClubRels = userClubRelMapper.selectByExample(example1);
        if (!CollectionUtils.isEmpty(cmsUserClubRels)) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_JOINED);
        }
        // 查询是否已申请加入
        CmsClubJoinApplyExample example = new CmsClubJoinApplyExample();
        example.createCriteria()
            .andClubIdEqualTo(cmsClubsJoinParam.getClubId())
            .andUserIdEqualTo(sysUserService.getCurrentUser().getId())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsClubJoinApply> cmsClubJoinApplies = clubJoinApplyMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsClubJoinApplies)) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_APPLY_JOIN);
        }

        CmsClubJoinApply cmsClubJoinApply = new CmsClubJoinApply();
        BeanUtils.copyProperties(cmsClubsJoinParam, cmsClubJoinApply);
        cmsClubJoinApply.setUserId(sysUserService.getCurrentUser().getId());
        cmsClubJoinApply.setCreateAt(new Date());
        cmsClubJoinApply.setHandleAt(null);
        cmsClubJoinApply.setState(ApplyStateEnum.PENDING.getValue());

        if (clubJoinApplyMapper.insert(cmsClubJoinApply) == 0) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        return cmsClubJoinApply;
    }

    @Override
    @CheckClubAuth(UserRoleEnum.CHIEF)
    public List<CmsClubsJoinDTO> listJoinClubApply(Integer clubId,
        CmsClubsJoinQuery cmsClubsJoinQuery, PaginationParam paginationParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return cmsClubJoinDAO.listClubJoinApply(cmsClubsJoinQuery, clubId);
    }

    @Override
    public CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsClubJoinApply cmsClubJoinApply = clubJoinApplyMapper
            .selectByPrimaryKey(cmsClubsAuditParam.getId());
        checkNotExistOrAudited(cmsClubJoinApply);

        //增加社长判定，只有社长才能审核加入申请
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubJoinApply.getClubId());
        if (cmsClub == null) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        if (!sysUserService.getCurrentUser().getId().equals(cmsClub.getChiefId())) {
            Asserts.forbidden(ApplyAuditErrorEnum.CAN_NOT_AUDIT_JOIN_APPLY);
        }

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsClubJoinApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //更新社团人数

            cmsClub.setMemberCount(cmsClub.getMemberCount() + 1);
            if (clubMapper.updateByPrimaryKeySelective(cmsClub) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            //更新加入申请
            cmsClubJoinApply.setState(ApplyStateEnum.ACTIVE.getValue());
            Date handleAt = new Date();
            cmsClubJoinApply.setHandleAt(handleAt);
            if (clubJoinApplyMapper.updateByPrimaryKeySelective(cmsClubJoinApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            //更新club_user表
            CmsUserClubRel cmsUserClubRel = new CmsUserClubRel();
            cmsUserClubRel.setUserId(cmsClubJoinApply.getUserId());
            cmsUserClubRel.setClubId(cmsClubJoinApply.getClubId());
            cmsUserClubRel.setRoleId(UserRoleEnum.MEMBER.getValue());
            cmsUserClubRel.setCredit(0);
            cmsUserClubRel.setHonorId(1);
            cmsUserClubRel.setJoinDate(handleAt);
            if (userClubRelMapper.insert(cmsUserClubRel) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            return cmsClubJoinApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            cmsClubJoinApply.setState(ApplyStateEnum.REJECTED.getValue());
            cmsClubJoinApply.setHandleAt(new Date());
            if (clubJoinApplyMapper.updateByPrimaryKeySelective(cmsClubJoinApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
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
        if (NotExistUtil.check(cmsClub)) {
            Asserts.notFound(ApplyAuditErrorEnum.CLUB_NOT_EXIST);
        }
        //前端页面社长没有退社按钮就不需要验证了
        //虽然感觉没必要但还是验证一下该用户是否是该社团成员
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria()
            .andClubIdEqualTo(cmsClubsQuitParam.getClubId())
            .andUserIdEqualTo(sysUserService.getCurrentUser().getId());
        List<CmsUserClubRel> cmsUserClubRels = userClubRelMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(cmsUserClubRels)) {
            Asserts.fail(ApplyAuditErrorEnum.NOT_MEMBER);
        }
        //添加退出记录
        CmsQuitNotice cmsQuitNotice = new CmsQuitNotice();
        cmsQuitNotice.setClubId(cmsClubsQuitParam.getClubId());
        cmsQuitNotice.setUserId(sysUserService.getCurrentUser().getId());
        cmsQuitNotice.setQuitDate(new Date());
        cmsQuitNotice.setReason(cmsClubsQuitParam.getReason());
        if (quitNoticeMapper.insert(cmsQuitNotice) == 0) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        //删除user_club表相关记录
        if (userClubRelMapper.deleteByExample(example) == 0) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        //更新club表相关记录人数

        cmsClub.setMemberCount(cmsClub.getMemberCount() - 1);
        if (clubMapper.updateByPrimaryKeySelective(cmsClub) == 0) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        return cmsQuitNotice;
    }

    @Override
    @CheckClubAuth(UserRoleEnum.CHIEF)
    public List<CmsClubsQuitDTO> listClubQuit(Integer clubId,
        CmsClubsQuitQuery cmsClubsQuitQuery,
        PaginationParam paginationParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return cmsClubQuitDAO.listClubQuit(cmsClubsQuitQuery,clubId);
    }

    //以下三个函数用于社长换届
    @Override
    public CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam) {
        //虽然感觉多余但是还是验证一下社团是否存在
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsChiefChangeParam.getClubId());
        if (cmsClub == null || cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()) {
            Asserts.notFound(ApplyAuditErrorEnum.CLUB_NOT_EXIST);
        }
        //验证是否是社长提出的解散
        if (!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())) {
            Asserts.forbidden(ApplyAuditErrorEnum.CAN_NOT_CHIEF_CHANGE);
        }
        //验证新社长是否存在
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(cmsClubsChiefChangeParam.getNewChiefName());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(sysUsers)) {
            Asserts.notFound(ApplyAuditErrorEnum.USER_NOT_EXIST);
        }
        SysUser newChief = sysUsers.get(0);
        //验证新社长是否是社员
        CmsUserClubRelExample example1 = new CmsUserClubRelExample();
        example1.createCriteria().andClubIdEqualTo(
            cmsClubsChiefChangeParam.getClubId()).andUserIdEqualTo(newChief.getId());
        List<CmsUserClubRel> userClubRels = userClubRelMapper.selectByExample(example1);
        if (CollectionUtils.isEmpty(userClubRels)) {
            Asserts.fail(ApplyAuditErrorEnum.NOT_MEMBER);
        }
        //验证是否存在PENDING状态的申请
        CmsChiefChangeApplyExample example2 = new CmsChiefChangeApplyExample();
        example2.createCriteria().andClubIdEqualTo(cmsClubsChiefChangeParam.getClubId())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsChiefChangeApply> cmsChiefChangeApplies = chiefChangeApplyMapper
            .selectByExample(example2);
        if (!CollectionUtils.isEmpty(cmsChiefChangeApplies)) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_APPLY_CHIEF_CHANG);
        }

        CmsChiefChangeApply cmsChiefChangeApply = new CmsChiefChangeApply();
        cmsChiefChangeApply.setClubId(cmsClubsChiefChangeParam.getClubId());
        cmsChiefChangeApply.setOldChiefId(sysUserService.getCurrentUser().getId());
        cmsChiefChangeApply.setNewChiefId(newChief.getId());
        cmsChiefChangeApply.setReason(cmsClubsChiefChangeParam.getReason());
        cmsChiefChangeApply.setCreateAt(new Date());
        cmsChiefChangeApply.setHandleAt(null);
        cmsChiefChangeApply.setState(ApplyStateEnum.PENDING.getValue());
        if (chiefChangeApplyMapper.insert(cmsChiefChangeApply) == 0) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        return cmsChiefChangeApply;
    }

    @Override
    @IsAdmin
    public List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(
        CmsClubsChiefChangeQuery cmsClubsChiefChangeQuery,
        PaginationParam paginationParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return cmsClubChiefChangeDAO.listClubChiefChangeApply(cmsClubsChiefChangeQuery);
    }

    @Override
    @Transactional
    public CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsChiefChangeApply cmsChiefChangeApply = chiefChangeApplyMapper
            .selectByPrimaryKey(cmsClubsAuditParam.getId());
        checkNotExistOrAudited(cmsChiefChangeApply);

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsChiefChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //更新社团社长
            CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsChiefChangeApply.getClubId());
            cmsClub.setChiefId(cmsChiefChangeApply.getNewChiefId());
            if (clubMapper.updateByPrimaryKeySelective(cmsClub) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            //老社长要不要退社有待讨论

            //更新user_club表的roleId
            //修改老社长roleId
            CmsUserClubRelExample example = new CmsUserClubRelExample();
            example.createCriteria().andClubIdEqualTo(cmsChiefChangeApply.getClubId())
                .andUserIdEqualTo(cmsChiefChangeApply.getOldChiefId());
            List<CmsUserClubRel> userClubRelOld = userClubRelMapper.selectByExample(example);
            userClubRelOld.get(0).setRoleId(UserRoleEnum.MEMBER.getValue());
            if (userClubRelMapper.updateByPrimaryKeySelective(userClubRelOld.get(0)) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            //修改新社长社长roleId
            CmsUserClubRelExample example1 = new CmsUserClubRelExample();
            example1.createCriteria().andClubIdEqualTo(cmsChiefChangeApply.getClubId())
                .andUserIdEqualTo(cmsChiefChangeApply.getNewChiefId());
            List<CmsUserClubRel> userClubRelNew = userClubRelMapper.selectByExample(example1);
            userClubRelNew.get(0).setRoleId(UserRoleEnum.CHIEF.getValue());
            if (userClubRelMapper.updateByPrimaryKeySelective(userClubRelNew.get(0)) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            //更新申请记录
            cmsChiefChangeApply.setState(ApplyStateEnum.ACTIVE.getValue());
            cmsChiefChangeApply.setHandleAt(new Date());
            if (chiefChangeApplyMapper.updateByPrimaryKeySelective(cmsChiefChangeApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            return cmsChiefChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            cmsChiefChangeApply.setState(ApplyStateEnum.REJECTED.getValue());
            cmsChiefChangeApply.setHandleAt(new Date());
            if (chiefChangeApplyMapper.updateByPrimaryKeySelective(cmsChiefChangeApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            return cmsChiefChangeApply;
        }
        return cmsChiefChangeApply;
    }

    //以下三个用于社团认证
    @Override
    public CmsOfficialChangeApply clubOfficialChange(
        CmsClubsCertificationsParam cmsClubsCertificationsParam) {
        //查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsClubsCertificationsParam.getClubId());
        if (cmsClub == null || cmsClub.getDeleteStatus() == DeleteStateEnum.Deleted.getValue()) {
            Asserts.notFound(ApplyAuditErrorEnum.CLUB_NOT_EXIST);
        }
        //验证是否是社长提出的认证
        if (!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())) {
            Asserts.forbidden(ApplyAuditErrorEnum.CAN_NOT_OFFICIAL_CHANGE);
        }
        //查询该社团是否是非认证社团
        if (cmsClub.getOfficialState() == ClubOfficialStateEnum.OFFICIAL.getValue()) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_CERTIFICATED);
        }
        // 查询是否已申请认证该社团
        CmsOfficialChangeApplyExample example = new CmsOfficialChangeApplyExample();
        example.createCriteria().andClubIdEqualTo(cmsClubsCertificationsParam.getClubId())
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        List<CmsOfficialChangeApply> cmsOfficialChangeApplies = officialChangeApplyMapper
            .selectByExample(example);
        if (!CollectionUtils.isEmpty(cmsOfficialChangeApplies)) {
            Asserts.fail(ApplyAuditErrorEnum.ALREADY_APPLY_OFFICIAL_CHANGE);
        }

        CmsOfficialChangeApply cmsOfficialChangeApply = new CmsOfficialChangeApply();
        cmsOfficialChangeApply.setClubId(cmsClubsCertificationsParam.getClubId());
        cmsOfficialChangeApply.setReason(cmsClubsCertificationsParam.getReason());
        cmsOfficialChangeApply.setCreateAt(new Date());
        cmsOfficialChangeApply.setHandleAt(null);
        cmsOfficialChangeApply.setState(ApplyStateEnum.PENDING.getValue());
        if (officialChangeApplyMapper.insert(cmsOfficialChangeApply) == 0) {
            Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
        }
        return cmsOfficialChangeApply;
    }

    @Override
    @IsAdmin
    public List<CmsClubsCertificationsDTO> listClubOfficialChange(
        CmsClubsCertificationsQuery cmsClubsCertificationsQuery,
        PaginationParam paginationParam) {

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return cmsClubCertificationDAO.listClubCertificationApply(cmsClubsCertificationsQuery);
    }

    @Override
    public CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam) {
        CmsOfficialChangeApply cmsOfficialChangeApply = officialChangeApplyMapper
            .selectByPrimaryKey(cmsClubsAuditParam.getId());
        checkNotExistOrAudited(cmsOfficialChangeApply);

        if (cmsClubsAuditParam.getState() == ApplyStateEnum.PENDING.getValue()) {
            return cmsOfficialChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.ACTIVE.getValue()) {
            //更新社团官方状态
            CmsClub cmsClub = clubMapper.selectByPrimaryKey(cmsOfficialChangeApply.getClubId());
            cmsClub.setOfficialState(ClubOfficialStateEnum.OFFICIAL.getValue());
            if (clubMapper.updateByPrimaryKeySelective(cmsClub) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            //更新申请记录
            cmsOfficialChangeApply.setState(ApplyStateEnum.ACTIVE.getValue());
            cmsOfficialChangeApply.setHandleAt(new Date());
            if (officialChangeApplyMapper.updateByPrimaryKeySelective(cmsOfficialChangeApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }

            return cmsOfficialChangeApply;
        }
        if (cmsClubsAuditParam.getState() == ApplyStateEnum.REJECTED.getValue()) {
            cmsOfficialChangeApply.setState(ApplyStateEnum.REJECTED.getValue());
            cmsOfficialChangeApply.setHandleAt(new Date());
            if (officialChangeApplyMapper.updateByPrimaryKeySelective(cmsOfficialChangeApply) == 0) {
                Asserts.fail(ApplyAuditErrorEnum.MAPPER_OPERATION_FAILED);
            }
            return cmsOfficialChangeApply;
        }
        return cmsOfficialChangeApply;
    }

    @Override
    @CheckClubAuth(UserRoleEnum.CHIEF)
    public List<CmsOfficialChangeApply> listMyClubOfficialChange(Integer clubId, PaginationParam paginationParam) {

        CmsOfficialChangeApplyExample example = new CmsOfficialChangeApplyExample();
        example.createCriteria().andClubIdEqualTo(clubId);
        example.setOrderByClause("state ASC ,create_at DESC ");
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return officialChangeApplyMapper.selectByExample(example);
    }

    @Override
    @CheckClubAuth(UserRoleEnum.CHIEF)
    public List<CmsChiefChangeApply> listMyClubChiefChange(Integer clubId, PaginationParam paginationParam) {

        CmsChiefChangeApplyExample example = new CmsChiefChangeApplyExample();
        example.createCriteria().andClubIdEqualTo(clubId);
        example.setOrderByClause("state ASC ,create_at DESC ");
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return chiefChangeApplyMapper.selectByExample(example);
    }

    @Override
    @CheckClubAuth(UserRoleEnum.CHIEF)
    public List<CmsClubDisbandApply> listMyClubDissolution(Integer clubId, PaginationParam paginationParam) {

        CmsClubDisbandApplyExample example = new CmsClubDisbandApplyExample();
        example.createCriteria().andClubIdEqualTo(clubId);
        example.setOrderByClause("state ASC ,create_at DESC ");
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return clubDisbandApplyMapper.selectByExample(example);
    }

    @Override
    public void updateExpiredApply() {
        LOGGER.info("close {} expired join club apply", updateExpiredJoinClubApply());
        LOGGER.info("close {} expired create club apply", updateExpiredClubCreationApply());
        LOGGER.info("close {} expired disband club apply", updateExpiredClubDisbandApply());
        LOGGER.info("close {} expired chief change apply", updateExpiredChiefChangeApply());
        LOGGER.info("close {} expired official change apply", updateExpiredOfficialChangeApply());
    }

    @Override
    public int updateExpiredJoinClubApply() {
        CmsClubJoinApply record = new CmsClubJoinApply();
        record.setState(ApplyStateEnum.REJECTED.getValue());
        DateTime deadline = DateUtil.offsetDay(new Date(), -applyExpireDay);
        CmsClubJoinApplyExample example = new CmsClubJoinApplyExample();
        example.createCriteria()
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue())
            .andCreateAtLessThan(deadline);
        return clubJoinApplyMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateExpiredClubCreationApply() {
        CmsClubCreateApply record = new CmsClubCreateApply();
        record.setState(ApplyStateEnum.REJECTED.getValue());
        DateTime deadline = DateUtil.offsetDay(new Date(), -applyExpireDay);
        CmsClubCreateApplyExample example = new CmsClubCreateApplyExample();
        example.createCriteria()
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue())
            .andCreateAtLessThan(deadline);
        return clubCreateApplyMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateExpiredClubDisbandApply() {
        CmsClubDisbandApply record = new CmsClubDisbandApply();
        record.setState(ApplyStateEnum.REJECTED.getValue());
        DateTime deadline = DateUtil.offsetDay(new Date(), -applyExpireDay);
        CmsClubDisbandApplyExample example = new CmsClubDisbandApplyExample();
        example.createCriteria()
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue())
            .andCreateAtLessThan(deadline);
        return clubDisbandApplyMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateExpiredChiefChangeApply() {
        CmsChiefChangeApply record = new CmsChiefChangeApply();
        record.setState(ApplyStateEnum.REJECTED.getValue());
        DateTime deadline = DateUtil.offsetDay(new Date(), -applyExpireDay);
        CmsChiefChangeApplyExample example = new CmsChiefChangeApplyExample();
        example.createCriteria()
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue())
            .andCreateAtLessThan(deadline);
        return chiefChangeApplyMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateExpiredOfficialChangeApply() {
        CmsOfficialChangeApply record = new CmsOfficialChangeApply();
        record.setState(ApplyStateEnum.REJECTED.getValue());
        DateTime deadline = DateUtil.offsetDay(new Date(), -applyExpireDay);
        CmsOfficialChangeApplyExample example = new CmsOfficialChangeApplyExample();
        example.createCriteria()
            .andStateEqualTo(ApplyStateEnum.PENDING.getValue())
            .andCreateAtLessThan(deadline);
        return officialChangeApplyMapper.updateByExampleSelective(record, example);
    }
}
