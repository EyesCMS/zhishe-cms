package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import edu.fzu.zhishe.cms.model.FmsUserLikePostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FmsUserLikePostMapper {
    long countByExample(FmsUserLikePostExample example);

    int deleteByExample(FmsUserLikePostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FmsUserLikePost record);

    int insertSelective(FmsUserLikePost record);

    List<FmsUserLikePost> selectByExample(FmsUserLikePostExample example);

    FmsUserLikePost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FmsUserLikePost record, @Param("example") FmsUserLikePostExample example);

    int updateByExample(@Param("record") FmsUserLikePost record, @Param("example") FmsUserLikePostExample example);

    int updateByPrimaryKeySelective(FmsUserLikePost record);

    int updateByPrimaryKey(FmsUserLikePost record);
}