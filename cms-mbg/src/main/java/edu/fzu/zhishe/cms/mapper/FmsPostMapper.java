package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.cms.model.FmsPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FmsPostMapper {
    long countByExample(FmsPostExample example);

    int deleteByExample(FmsPostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FmsPost record);

    int insertSelective(FmsPost record);

    List<FmsPost> selectByExample(FmsPostExample example);

    FmsPost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FmsPost record, @Param("example") FmsPostExample example);

    int updateByExample(@Param("record") FmsPost record, @Param("example") FmsPostExample example);

    int updateByPrimaryKeySelective(FmsPost record);

    int updateByPrimaryKey(FmsPost record);
}