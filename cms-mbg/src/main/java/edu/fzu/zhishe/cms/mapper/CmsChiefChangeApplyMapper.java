package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsChiefChangeApply;
import edu.fzu.zhishe.cms.model.CmsChiefChangeApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsChiefChangeApplyMapper {
    long countByExample(CmsChiefChangeApplyExample example);

    int deleteByExample(CmsChiefChangeApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsChiefChangeApply record);

    int insertSelective(CmsChiefChangeApply record);

    List<CmsChiefChangeApply> selectByExample(CmsChiefChangeApplyExample example);

    CmsChiefChangeApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsChiefChangeApply record, @Param("example") CmsChiefChangeApplyExample example);

    int updateByExample(@Param("record") CmsChiefChangeApply record, @Param("example") CmsChiefChangeApplyExample example);

    int updateByPrimaryKeySelective(CmsChiefChangeApply record);

    int updateByPrimaryKey(CmsChiefChangeApply record);
}