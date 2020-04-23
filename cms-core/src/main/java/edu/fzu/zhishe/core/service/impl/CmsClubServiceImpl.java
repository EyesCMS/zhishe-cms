package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.CmsClubCreateApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;
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
    CmsClubMapper clubMapper;

    @Override
    public CmsClubCreateApply clubCreate(CmsClubsCreationsParam clubsCreationsParam) {
        // 查询是否已申请该社团
        CmsClubCreateApplyExample example1 = new CmsClubCreateApplyExample();
        example1.createCriteria().andClubNameEqualTo(clubsCreationsParam.getClub_name());
        List<CmsClubCreateApply> cmsClubCreateApplies = clubCreateApplyMapper.selectByExample(example1);
        if (!CollectionUtils.isEmpty(cmsClubCreateApplies)) {
            Asserts.fail(" 该社团已经申请 ");
        }
        //查询是否已存在该社团
        CmsClubExample example2 = new CmsClubExample();
        example2.createCriteria().andNameEqualTo(clubsCreationsParam.getClub_name());
        List<CmsClub> cmsClubs = clubMapper.selectByExample(example2);
        if (!CollectionUtils.isEmpty(cmsClubs)) {
            Asserts.fail(" 该社团已经存在 ");
        }

        SysUserService sysUserService = new SysUserServiceImpl();
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
    public CmsClubCreateApply clubAudit(CmsClubsCreationsAuditParam cmsClubsCreationsAuditParam) {
        // 查询是否已有该社团申请
        CmsClubCreateApply cmsClubCreateApply = clubCreateApplyMapper.selectByPrimaryKey(cmsClubsCreationsAuditParam.getId());
        if (cmsClubCreateApply == null) {
            Asserts.fail(" 该社团申请不存在 ");
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
}
