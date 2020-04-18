package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.SysUserRoleRel;
import edu.fzu.zhishe.cms.model.SysUserRoleRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleRelMapper {
    long countByExample(SysUserRoleRelExample example);

    int deleteByExample(SysUserRoleRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRoleRel record);

    int insertSelective(SysUserRoleRel record);

    List<SysUserRoleRel> selectByExample(SysUserRoleRelExample example);

    SysUserRoleRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserRoleRel record, @Param("example") SysUserRoleRelExample example);

    int updateByExample(@Param("record") SysUserRoleRel record, @Param("example") SysUserRoleRelExample example);

    int updateByPrimaryKeySelective(SysUserRoleRel record);

    int updateByPrimaryKey(SysUserRoleRel record);
}