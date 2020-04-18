package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsUserLabel;
import edu.fzu.zhishe.cms.model.CmsUserLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsUserLabelMapper {
    long countByExample(CmsUserLabelExample example);

    int deleteByExample(CmsUserLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsUserLabel record);

    int insertSelective(CmsUserLabel record);

    List<CmsUserLabel> selectByExample(CmsUserLabelExample example);

    CmsUserLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsUserLabel record, @Param("example") CmsUserLabelExample example);

    int updateByExample(@Param("record") CmsUserLabel record, @Param("example") CmsUserLabelExample example);

    int updateByPrimaryKeySelective(CmsUserLabel record);

    int updateByPrimaryKey(CmsUserLabel record);
}