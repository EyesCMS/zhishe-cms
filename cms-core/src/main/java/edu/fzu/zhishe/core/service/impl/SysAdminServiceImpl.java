package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.*;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.ApplyStateEnum;
import edu.fzu.zhishe.core.dto.SysAdminAuditedDTO;
import edu.fzu.zhishe.core.dto.SysAdminClubSpeciesDTO;
import edu.fzu.zhishe.core.dto.SysAdminNewUsersDTO;
import edu.fzu.zhishe.core.service.SysAdminService;
import edu.fzu.zhishe.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    SysUserService userService;

    @Autowired
    CmsClubCreateApplyMapper createApplyMapper;

    @Autowired
    CmsClubDisbandApplyMapper disbandApplyMapper;

    @Autowired
    CmsActivityMapper activityMapper;

    @Autowired
    CmsChiefChangeApplyMapper changeApplyMapper;

    @Autowired
    CmsOfficialChangeApplyMapper officialChangeApplyMapper;

    @Override
    public SysAdminAuditedDTO unaudited() {
        SysUser user = userService.getCurrentUser();
        if (user == null || user.getIsAdmin() == 0) {
            Asserts.forbidden("非管理员无法获取统计数据");
        }

        SysAdminAuditedDTO dto = new SysAdminAuditedDTO();
        CmsClubCreateApplyExample example1 = new CmsClubCreateApplyExample();
        CmsClubDisbandApplyExample example2 = new CmsClubDisbandApplyExample();
        CmsActivityExample example3 = new CmsActivityExample();
        CmsChiefChangeApplyExample example4 = new CmsChiefChangeApplyExample();
        CmsOfficialChangeApplyExample example5 = new CmsOfficialChangeApplyExample();

        example1.createCriteria().andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        example2.createCriteria().andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        example3.createCriteria().andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        example4.createCriteria().andStateEqualTo(ApplyStateEnum.PENDING.getValue());
        example5.createCriteria().andStateEqualTo(ApplyStateEnum.PENDING.getValue());

        dto.setCreateNumber((int)createApplyMapper.countByExample(example1));
        dto.setDismissNumber((int)disbandApplyMapper.countByExample(example2));
        dto.setActivityNumber((int)activityMapper.countByExample(example3));
        dto.setChangeNumber((int)changeApplyMapper.countByExample(example4));
        dto.setIdentifyNumber((int)officialChangeApplyMapper.countByExample(example5));

        return dto;
    }

    @Override
    public SysAdminNewUsersDTO newUsers() {
        SysAdminNewUsersDTO dto = new SysAdminNewUsersDTO();

        return dto;
    }

    @Override
    public SysAdminClubSpeciesDTO clubSpecies() {
        SysAdminClubSpeciesDTO dto = new SysAdminClubSpeciesDTO();

        return dto;
    }
}
