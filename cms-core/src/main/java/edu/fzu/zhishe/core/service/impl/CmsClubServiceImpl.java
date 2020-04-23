package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.CmsClubCreateApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubDisbandApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import edu.fzu.zhishe.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 *社团管理服务层
 *
 * @author yang
 */

@Service
public class CmsClubServiceImpl  implements CmsClubService {

    @Autowired
    CmsClubCreateApplyMapper clubCreateApplyMapper;

    @Autowired
    CmsClubDisbandApplyMapper clubDisbandApplyMapper;

    @Autowired
    CmsClubMapper clubMapper;

    @Autowired
    SysUserService sysUserService;

    //一下三个函数用于社团创建
    @Override
    public CmsClubCreateApply clubCreate(CmsClubsCreationsParam clubsCreationsParam) {
        // 查询是否已申请创建该社团
        CmsClubCreateApplyExample example1 = new CmsClubCreateApplyExample();
        example1.createCriteria().andClubNameEqualTo(clubsCreationsParam.getClub_name());
        List<CmsClubCreateApply> cmsClubCreateApplies = clubCreateApplyMapper.selectByExample(example1);
        if (!CollectionUtils.isEmpty(cmsClubCreateApplies)) {
            Asserts.fail(" 该社团已经申请创建，请等待审核 ");
        }
        //查询是否已存在该社团
        CmsClubExample example2 = new CmsClubExample();
        example2.createCriteria().andNameEqualTo(clubsCreationsParam.getClub_name());
        List<CmsClub> cmsClubs = clubMapper.selectByExample(example2);
        if (!CollectionUtils.isEmpty(cmsClubs)) {
            Asserts.fail(" 该社团已经存在 ");
        }

        //SysUserService sysUserService = new SysUserServiceImpl();
        SysUser sysUser = sysUserService.getCurrentUser();
        CmsClubCreateApply cmsClubCreateApply = new CmsClubCreateApply();

        cmsClubCreateApply.setApplicant(clubsCreationsParam.getApplicant());
        cmsClubCreateApply.setClubName(clubsCreationsParam.getClub_name());
        cmsClubCreateApply.setType(clubsCreationsParam.getType());
        cmsClubCreateApply.setOfficialState(clubsCreationsParam.isOfficial_state());
        cmsClubCreateApply.setReason(clubsCreationsParam.getReason());
        cmsClubCreateApply.setCreateAt(new Date());
        cmsClubCreateApply.setHandleAt(null);
        cmsClubCreateApply.setState(0);
        cmsClubCreateApply.setUserId(sysUser.getId());
        //System.out.println(cmsClubCreateApply.getCreateAt());
        clubCreateApplyMapper.insert(cmsClubCreateApply);
        return cmsClubCreateApply;
    }

    @Override
    public List<CmsClubCreateApply> getClubCreateList() {
        return clubCreateApplyMapper.selectByExample(null);
    }

    @Override
    public CmsClubCreateApply clubCreationsAudit(CmsClubsCreationsAuditParam cmsClubsCreationsAuditParam) {
        // 查询是否已有该社团申请
        CmsClubCreateApply cmsClubCreateApply = clubCreateApplyMapper.selectByPrimaryKey(cmsClubsCreationsAuditParam.getId());
        if (cmsClubCreateApply == null) {
            Asserts.fail(" 该社团创建申请不存在 ");
        }
        if(cmsClubsCreationsAuditParam.getState()!=0&&cmsClubsCreationsAuditParam.getState()!=1&&cmsClubsCreationsAuditParam.getState()!=2){
            Asserts.fail(" 该申请状态码不正确 ");
        }

        if(cmsClubsCreationsAuditParam.getState()==0){
            return cmsClubCreateApply;
        }

        if(cmsClubsCreationsAuditParam.getState()==1){
            //创建社团
            CmsClub cmsClub = new CmsClub();

            cmsClub.setName(cmsClubCreateApply.getClubName());
            cmsClub.setChiefId(cmsClubCreateApply.getUserId());
            cmsClub.setMemberCount(1);
            cmsClub.setOfficialState(cmsClubCreateApply.getOfficialState()?1:0);
            cmsClub.setCreateAt(new Date());
            clubMapper.insert(cmsClub);

            //更新申请记录
            cmsClubCreateApply.setHandleAt(new Date());
            cmsClubCreateApply.setState(1);
            clubCreateApplyMapper.updateByPrimaryKey(cmsClubCreateApply);
            return cmsClubCreateApply;
        }
        if(cmsClubsCreationsAuditParam.getState()==2){
            //更新申请记录
            cmsClubCreateApply.setHandleAt(new Date());
            cmsClubCreateApply.setState(2);
            clubCreateApplyMapper.updateByPrimaryKey(cmsClubCreateApply);
            return cmsClubCreateApply;
        }
        return cmsClubCreateApply;
    }

    //以下三个函数用于社团解散
    @Override
    public CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam) {
        // 查询是否已申请解散该社团
        if (!(clubDisbandApplyMapper.selectByPrimaryKey(clubsDisbandParam.getClub_id()) == null)) {
            Asserts.fail(" 该社团已经申请解散，请等待审核 ");
        }
        //查询是否已存在该社团
        if (clubMapper.selectByPrimaryKey(clubsDisbandParam.getClub_id()) == null) {
            Asserts.fail(" 该社团不存在 ");
        }

        CmsClubDisbandApply cmsClubDisbandApply = new CmsClubDisbandApply();

        cmsClubDisbandApply.setClubId(clubsDisbandParam.getClub_id());
        cmsClubDisbandApply.setCreateAt(new Date());
        cmsClubDisbandApply.setHandleAt(null);
        cmsClubDisbandApply.setReason(clubsDisbandParam.getReason());
        cmsClubDisbandApply.setState(0);

        clubDisbandApplyMapper.insert(cmsClubDisbandApply);
        return cmsClubDisbandApply;
    }

    @Override
    public List<CmsClubDisbandApply> getClubDisbandList() {
        return clubDisbandApplyMapper.selectByExample(null);
    }

    @Override
    public CmsClubDisbandApply clubDissolutionsAudit(CmsClubsDisbandAuditParam cmsClubsDisbandAuditParam) {
        CmsClubDisbandApply cmsClubDisbandApply = clubDisbandApplyMapper.selectByPrimaryKey(cmsClubsDisbandAuditParam.getId());
        if(cmsClubDisbandApply == null){
            Asserts.fail(" 该社团解散申请不存在 ");
        }
        if(cmsClubsDisbandAuditParam.getState()!=0&&cmsClubsDisbandAuditParam.getState()!=1&&cmsClubsDisbandAuditParam.getState()!=2){
            Asserts.fail(" 该申请状态码不正确 ");
        }

        if(cmsClubsDisbandAuditParam.getState()==0){
            return cmsClubDisbandApply;
        }
        if(cmsClubsDisbandAuditParam.getState()==1){
            //删除社团
            //System.out.println(cmsClubDisbandApply.toString());
            //System.out.println(cmsClubDisbandApply.getClubId());

            //4.25白天在讨论
            clubDisbandApplyMapper.deleteByPrimaryKey(cmsClubsDisbandAuditParam.getId());
            clubMapper.deleteByPrimaryKey(cmsClubDisbandApply.getClubId());


//            //更新申请记录
//            cmsClubDisbandApply.setHandleAt(new Date());
//            cmsClubDisbandApply.setState(1);
//            clubDisbandApplyMapper.updateByPrimaryKeySelective(cmsClubDisbandApply);
            return cmsClubDisbandApply;
        }
        if(cmsClubsDisbandAuditParam.getState()==2){
            //更新申请记录
            cmsClubDisbandApply.setHandleAt(new Date());
            cmsClubDisbandApply.setState(2);
            clubDisbandApplyMapper.updateByPrimaryKeySelective(cmsClubDisbandApply);
            return cmsClubDisbandApply;
        }

        return cmsClubDisbandApply;
    }
}
