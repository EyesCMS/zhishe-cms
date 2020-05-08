package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubExample;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import edu.fzu.zhishe.cms.model.SysPermission;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dao.SysRolePermissionDAO;
import edu.fzu.zhishe.core.service.CmsClubService;
import edu.fzu.zhishe.core.service.SysRoleService;
import edu.fzu.zhishe.core.service.SysUserCacheService;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRolePermissionDAO rolePermissionDAO;

    @Autowired
    private CmsUserClubRelMapper userClubRelMapper;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserCacheService userCacheService;

    @Override
    public List<SysPermission> listPermission(Integer roleId) {
        return rolePermissionDAO.listPermissionByRoleId(roleId);
    }

    @Override
    public void switchCurrentRole(Integer clubId) {
        SysUser currentUser = userService.getCurrentUser();
        // 4. is admin
        // 3. is chief
        // 2. is member
        if (currentUser.getIsAdmin() == 1) {
            return ;
        }

        LOGGER.info("old user role is {} ", currentUser.getCurrentRole());
        Integer oldUserRole = currentUser.getCurrentRole();

        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria()
            .andUserIdEqualTo(currentUser.getId())
            .andClubIdEqualTo(clubId);
        List<CmsUserClubRel> userClubRels = userClubRelMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userClubRels)) {
            // TODO: check whether this case is OK
            Asserts.fail("switch role failed");
        }

        CmsUserClubRel userClubRel = userClubRels.get(0);
        currentUser.setCurrentRole(userClubRel.getRoleId());

        LOGGER.info("new user role is {} ", currentUser.getCurrentRole());
        if (oldUserRole.equals(currentUser.getCurrentRole())) {
            // no need for updating
            // TODO: need this?
            return ;
        }

        userService.updateUserSelective(currentUser);
        userCacheService.delUser(currentUser.getId());
    }

    @Override
    public void resetCurrentRole() {
        SysUser currentUser = userService.getCurrentUser();
        if (currentUser.getIsAdmin() != 1) {
            SysUser user = new SysUser() {{
                setId(currentUser.getId());
                setCurrentRole(UserRoleEnum.NORMAL.getValue());
            }};
            userService.updateUserSelective(user);
        }
    }
}
