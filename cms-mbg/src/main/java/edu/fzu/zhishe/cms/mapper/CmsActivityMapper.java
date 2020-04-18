package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.cms.model.CmsActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsActivityMapper {
    long countByExample(CmsActivityExample example);

    int deleteByExample(CmsActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsActivity record);

    int insertSelective(CmsActivity record);

    List<CmsActivity> selectByExample(CmsActivityExample example);

    CmsActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsActivity record, @Param("example") CmsActivityExample example);

    int updateByExample(@Param("record") CmsActivity record, @Param("example") CmsActivityExample example);

    int updateByPrimaryKeySelective(CmsActivity record);

    int updateByPrimaryKey(CmsActivity record);
}