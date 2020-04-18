package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsUserActivityRemark;
import edu.fzu.zhishe.cms.model.CmsUserActivityRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsUserActivityRemarkMapper {
    long countByExample(CmsUserActivityRemarkExample example);

    int deleteByExample(CmsUserActivityRemarkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsUserActivityRemark record);

    int insertSelective(CmsUserActivityRemark record);

    List<CmsUserActivityRemark> selectByExample(CmsUserActivityRemarkExample example);

    CmsUserActivityRemark selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsUserActivityRemark record, @Param("example") CmsUserActivityRemarkExample example);

    int updateByExample(@Param("record") CmsUserActivityRemark record, @Param("example") CmsUserActivityRemarkExample example);

    int updateByPrimaryKeySelective(CmsUserActivityRemark record);

    int updateByPrimaryKey(CmsUserActivityRemark record);
}