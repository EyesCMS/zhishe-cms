package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.SysPermission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public interface SysRolePermissionDAO {

    List<SysPermission> listPermissionByRoleId(@Param("id") Integer roleId);
}
