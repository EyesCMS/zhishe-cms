package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.SysRolePermissionRel;
import edu.fzu.zhishe.cms.model.SysRolePermissionRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolePermissionRelMapper {
    long countByExample(SysRolePermissionRelExample example);

    int deleteByExample(SysRolePermissionRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermissionRel record);

    int insertSelective(SysRolePermissionRel record);

    List<SysRolePermissionRel> selectByExample(SysRolePermissionRelExample example);

    SysRolePermissionRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRolePermissionRel record, @Param("example") SysRolePermissionRelExample example);

    int updateByExample(@Param("record") SysRolePermissionRel record, @Param("example") SysRolePermissionRelExample example);

    int updateByPrimaryKeySelective(SysRolePermissionRel record);

    int updateByPrimaryKey(SysRolePermissionRel record);
}