package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsClubLabelRel;
import edu.fzu.zhishe.cms.model.CmsClubLabelRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubLabelRelMapper {
    long countByExample(CmsClubLabelRelExample example);

    int deleteByExample(CmsClubLabelRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsClubLabelRel record);

    int insertSelective(CmsClubLabelRel record);

    List<CmsClubLabelRel> selectByExample(CmsClubLabelRelExample example);

    CmsClubLabelRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsClubLabelRel record, @Param("example") CmsClubLabelRelExample example);

    int updateByExample(@Param("record") CmsClubLabelRel record, @Param("example") CmsClubLabelRelExample example);

    int updateByPrimaryKeySelective(CmsClubLabelRel record);

    int updateByPrimaryKey(CmsClubLabelRel record);
}