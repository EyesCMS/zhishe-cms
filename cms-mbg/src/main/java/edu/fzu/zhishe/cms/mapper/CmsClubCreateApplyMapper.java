package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsClubCreateApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubCreateApplyMapper {
    long countByExample(CmsClubCreateApplyExample example);

    int deleteByExample(CmsClubCreateApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsClubCreateApply record);

    int insertSelective(CmsClubCreateApply record);

    List<CmsClubCreateApply> selectByExample(CmsClubCreateApplyExample example);

    CmsClubCreateApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsClubCreateApply record, @Param("example") CmsClubCreateApplyExample example);

    int updateByExample(@Param("record") CmsClubCreateApply record, @Param("example") CmsClubCreateApplyExample example);

    int updateByPrimaryKeySelective(CmsClubCreateApply record);

    int updateByPrimaryKey(CmsClubCreateApply record);
}