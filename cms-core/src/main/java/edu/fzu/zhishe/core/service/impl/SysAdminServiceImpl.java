package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.date.DateUtil;
import edu.fzu.zhishe.cms.mapper.*;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.config.CmsSecurityConfig;
import edu.fzu.zhishe.core.constant.ApplyStateEnum;
import edu.fzu.zhishe.core.dao.SysAdminDAO;
import edu.fzu.zhishe.core.dto.SysAdminAuditedDTO;
import edu.fzu.zhishe.core.dto.SysAdminClubSpeciesDTO;
import edu.fzu.zhishe.core.dto.SysAdminClubTypeAmount;
import edu.fzu.zhishe.core.dto.SysAdminNewUsersDTO;
import edu.fzu.zhishe.core.param.SysAdminNewUsersQuery;
import edu.fzu.zhishe.core.service.SysAdminService;
import edu.fzu.zhishe.core.service.SysUserCacheService;
import edu.fzu.zhishe.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    SysUserCacheService cacheService;

    @Autowired
    CmsClubMapper clubMapper;

    @Autowired
    SysAdminDAO adminDAO;

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
    public SysAdminNewUsersDTO newUsers(SysAdminNewUsersQuery query) {
        SysAdminNewUsersDTO dto = new SysAdminNewUsersDTO();
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria cri = example.createCriteria();
        if (query.endDate != null) {
            cri.andRegisterDateLessThanOrEqualTo(query.endDate);
        }
        if (query.startDate != null) {
            cri.andRegisterDateGreaterThan(query.startDate);
        }
        example.setOrderByClause("register_date desc");
        //DateUtil.betweenDay()query.startDate

        return dto;
    }

    @Override
    public SysAdminClubSpeciesDTO clubSpecies() {
        SysUser user = userService.getCurrentUser();
        if (user == null || user.getIsAdmin() == 0) {
            Asserts.forbidden("非管理员无法获取统计数据");
        }

        SysAdminClubSpeciesDTO dto = new SysAdminClubSpeciesDTO();
        List<SysAdminClubTypeAmount> array = adminDAO.getClubTypes();

        if (array.isEmpty()) {
            Asserts.notFound("没有数据");
        }

        List<String> types = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (SysAdminClubTypeAmount type : array) {
            types.add(type.getType());
            numbers.add(type.getNumber());
        }
        dto.setClubSpecies(types);
        dto.setClubSpeciesNumber(numbers);
        return dto;
    }
}
