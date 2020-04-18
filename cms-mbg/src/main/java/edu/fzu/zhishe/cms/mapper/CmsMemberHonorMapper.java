package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsMemberHonor;
import edu.fzu.zhishe.cms.model.CmsMemberHonorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsMemberHonorMapper {
    long countByExample(CmsMemberHonorExample example);

    int deleteByExample(CmsMemberHonorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsMemberHonor record);

    int insertSelective(CmsMemberHonor record);

    List<CmsMemberHonor> selectByExample(CmsMemberHonorExample example);

    CmsMemberHonor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsMemberHonor record, @Param("example") CmsMemberHonorExample example);

    int updateByExample(@Param("record") CmsMemberHonor record, @Param("example") CmsMemberHonorExample example);

    int updateByPrimaryKeySelective(CmsMemberHonor record);

    int updateByPrimaryKey(CmsMemberHonor record);
}