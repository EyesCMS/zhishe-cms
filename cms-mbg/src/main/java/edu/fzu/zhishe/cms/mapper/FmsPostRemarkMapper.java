package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.FmsPostRemark;
import edu.fzu.zhishe.cms.model.FmsPostRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FmsPostRemarkMapper {
    long countByExample(FmsPostRemarkExample example);

    int deleteByExample(FmsPostRemarkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FmsPostRemark record);

    int insertSelective(FmsPostRemark record);

    List<FmsPostRemark> selectByExample(FmsPostRemarkExample example);

    FmsPostRemark selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FmsPostRemark record, @Param("example") FmsPostRemarkExample example);

    int updateByExample(@Param("record") FmsPostRemark record, @Param("example") FmsPostRemarkExample example);

    int updateByPrimaryKeySelective(FmsPostRemark record);

    int updateByPrimaryKey(FmsPostRemark record);
}