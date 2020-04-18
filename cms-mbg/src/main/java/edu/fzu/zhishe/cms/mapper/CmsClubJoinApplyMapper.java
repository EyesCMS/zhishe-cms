package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsClubJoinApply;
import edu.fzu.zhishe.cms.model.CmsClubJoinApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubJoinApplyMapper {
    long countByExample(CmsClubJoinApplyExample example);

    int deleteByExample(CmsClubJoinApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsClubJoinApply record);

    int insertSelective(CmsClubJoinApply record);

    List<CmsClubJoinApply> selectByExample(CmsClubJoinApplyExample example);

    CmsClubJoinApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsClubJoinApply record, @Param("example") CmsClubJoinApplyExample example);

    int updateByExample(@Param("record") CmsClubJoinApply record, @Param("example") CmsClubJoinApplyExample example);

    int updateByPrimaryKeySelective(CmsClubJoinApply record);

    int updateByPrimaryKey(CmsClubJoinApply record);
}