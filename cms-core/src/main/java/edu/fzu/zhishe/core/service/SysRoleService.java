package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.SysPermission;
import java.util.List;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public interface SysRoleService {

    List<SysPermission> listPermission(Integer roleId);

    /**
     * switch current role based on club
     */
    void switchCurrentRole(Integer clubId);

    /**
     * reset current role
     */
    void resetCurrentRole();
}
