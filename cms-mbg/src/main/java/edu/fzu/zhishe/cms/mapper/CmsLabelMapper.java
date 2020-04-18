package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsLabel;
import edu.fzu.zhishe.cms.model.CmsLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsLabelMapper {
    long countByExample(CmsLabelExample example);

    int deleteByExample(CmsLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsLabel record);

    int insertSelective(CmsLabel record);

    List<CmsLabel> selectByExample(CmsLabelExample example);

    CmsLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsLabel record, @Param("example") CmsLabelExample example);

    int updateByExample(@Param("record") CmsLabel record, @Param("example") CmsLabelExample example);

    int updateByPrimaryKeySelective(CmsLabel record);

    int updateByPrimaryKey(CmsLabel record);
}