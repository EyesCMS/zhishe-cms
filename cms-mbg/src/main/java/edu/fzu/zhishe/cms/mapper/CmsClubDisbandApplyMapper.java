package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsClubDisbandApply;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubDisbandApplyMapper {
    long countByExample(CmsClubDisbandApplyExample example);

    int deleteByExample(CmsClubDisbandApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsClubDisbandApply record);

    int insertSelective(CmsClubDisbandApply record);

    List<CmsClubDisbandApply> selectByExample(CmsClubDisbandApplyExample example);

    CmsClubDisbandApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsClubDisbandApply record, @Param("example") CmsClubDisbandApplyExample example);

    int updateByExample(@Param("record") CmsClubDisbandApply record, @Param("example") CmsClubDisbandApplyExample example);

    int updateByPrimaryKeySelective(CmsClubDisbandApply record);

    int updateByPrimaryKey(CmsClubDisbandApply record);
}