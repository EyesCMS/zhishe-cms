package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsUserActivityStar;
import edu.fzu.zhishe.cms.model.CmsUserActivityStarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsUserActivityStarMapper {
    long countByExample(CmsUserActivityStarExample example);

    int deleteByExample(CmsUserActivityStarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsUserActivityStar record);

    int insertSelective(CmsUserActivityStar record);

    List<CmsUserActivityStar> selectByExample(CmsUserActivityStarExample example);

    CmsUserActivityStar selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsUserActivityStar record, @Param("example") CmsUserActivityStarExample example);

    int updateByExample(@Param("record") CmsUserActivityStar record, @Param("example") CmsUserActivityStarExample example);

    int updateByPrimaryKeySelective(CmsUserActivityStar record);

    int updateByPrimaryKey(CmsUserActivityStar record);
}