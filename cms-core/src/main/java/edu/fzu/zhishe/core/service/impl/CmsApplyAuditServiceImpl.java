package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsChiefChangeApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubCreateApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubDisbandApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubJoinApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.mapper.CmsOfficialChangeApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsQuitNoticeMapper;
import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.mapper.SysUserMapper;
import edu.fzu.zhishe.cms.model.CmsChiefChangeApply;
import edu.fzu.zhishe.cms.model.CmsChiefChangeApplyExample;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsClubCreateApplyExample;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApply;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApplyExample;
import edu.fzu.zhishe.cms.model.CmsClubExample;
import edu.fzu.zhishe.cms.model.CmsClubJoinApply;
import edu.fzu.zhishe.cms.model.CmsClubJoinApplyExample;
import edu.fzu.zhishe.cms.model.CmsOfficialChangeApply;
import edu.fzu.zhishe.cms.model.CmsOfficialChangeApplyExample;
import edu.fzu.zhishe.cms.model.CmsQuitNotice;
import edu.fzu.zhishe.cms.model.CmsQuitNoticeExample;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.cms.model.SysUserExample;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.annotation.IsAdmin;
import edu.fzu.zhishe.core.constant.ApplyStateEnum;
import edu.fzu.zhishe.core.constant.ClubOfficialStateEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
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
import edu.fzu.zhishe.core.param.QueryParam;
import edu.fzu.zhishe.core.service.CmsApplyAuditService;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author yang
 */
@Service
public class CmsApplyAuditServiceImpl implements CmsApplyAuditService {

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
    @IsAdmin
    public List<CmsClubsCreationsDTO> listClubCreationApply(
        CmsClubsCreationsQuery cmsClubsCreationsQuery, PaginationParam paginationParam) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return cmsClubCreationDAO.listClubCreationApply(cmsClubsCreationsQuery);
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
    public List<CmsClubsDisbandDTO> listClubDisbandApply(
        CmsClubsDisbandQuery cmsClubsDisbandQuery, PaginationParam paginationParam) {
        if(sysUserService.getCurrentUser().getIsAdmin()==0){
            Asserts.fail(" 您不是社团管理员无权查看 ");
        }
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        List<CmsClubsDisbandDTO> cmsClubsDisbandDTOList = cmsClubDisbandDAO.listClubDisbandApply(
            cmsClubsDisbandQuery);
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
    public List<CmsClubsJoinDTO> listJoinClubApply(Integer clubId,
        CmsClubsJoinQuery cmsClubsJoinQuery, PaginationParam paginationParam) {
        // 查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(clubId);
        if ( cmsClub == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        if(!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())){
            Asserts.fail(" 您不是该社团社长无权查看 ");
        }
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        List<CmsClubsJoinDTO> cmsClubsJoinDTOList = cmsClubJoinDAO.listClubJoinApply(
            cmsClubsJoinQuery,clubId);
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
    public List<CmsClubsQuitDTO> listClubQuit(Integer clubId,
        CmsClubsQuitQuery cmsClubsQuitQuery, PaginationParam paginationParam) {
        // 查询是否已存在该社团
        CmsClub cmsClub = clubMapper.selectByPrimaryKey(clubId);
        if ( cmsClub == null) {
            Asserts.fail(" 该社团不存在 ");
        }
        if(!cmsClub.getChiefId().equals(sysUserService.getCurrentUser().getId())){
            Asserts.fail(" 您不是该社团社长无权查看 ");
        }
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        List<CmsClubsQuitDTO> cmsClubsQuitDTOList = cmsClubQuitDAO.listClubQuit(cmsClubsQuitQuery,clubId);
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
    public List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(
        CmsClubsChiefChangeQuery cmsClubsChiefChangeQuery, PaginationParam paginationParam) {
        if(sysUserService.getCurrentUser().getIsAdmin()==0){
            Asserts.fail(" 您不是社团管理员无权查看 ");
        }
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        List<CmsClubsChiefChangeDTO> cmsClubsChiefChangeDTOList = cmsClubChiefChangeDAO.listClubChiefChangeApply(
            cmsClubsChiefChangeQuery);
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
    public CmsOfficialChangeApply clubOfficialChange(
        CmsClubsCertificationsParam cmsClubsCertificationsParam) {
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
    public List<CmsClubsCertificationsDTO> listClubOfficialChange(
        CmsClubsCertificationsQuery cmsClubsCertificationsQuery, PaginationParam paginationParam) {
        if(sysUserService.getCurrentUser().getIsAdmin()==0){
            Asserts.fail(" 您不是社团管理员无权查看 ");
        }
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        List<CmsClubsCertificationsDTO> cmsClubsCertificationsDTOList = cmsClubCertificationDAO.listClubCertificationApply(
            cmsClubsCertificationsQuery);
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
}
