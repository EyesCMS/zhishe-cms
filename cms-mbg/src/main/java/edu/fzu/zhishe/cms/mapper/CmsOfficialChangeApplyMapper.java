package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsOfficialChangeApply;
import edu.fzu.zhishe.cms.model.CmsOfficialChangeApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsOfficialChangeApplyMapper {
    long countByExample(CmsOfficialChangeApplyExample example);

    int deleteByExample(CmsOfficialChangeApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsOfficialChangeApply record);

    int insertSelective(CmsOfficialChangeApply record);

    List<CmsOfficialChangeApply> selectByExample(CmsOfficialChangeApplyExample example);

    CmsOfficialChangeApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsOfficialChangeApply record, @Param("example") CmsOfficialChangeApplyExample example);

    int updateByExample(@Param("record") CmsOfficialChangeApply record, @Param("example") CmsOfficialChangeApplyExample example);

    int updateByPrimaryKeySelective(CmsOfficialChangeApply record);

    int updateByPrimaryKey(CmsOfficialChangeApply record);
}