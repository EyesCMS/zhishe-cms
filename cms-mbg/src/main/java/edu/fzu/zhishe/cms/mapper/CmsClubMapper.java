package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsClubDO;
import edu.fzu.zhishe.cms.model.CmsClubExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubMapper {
    long countByExample(CmsClubExample example);

    int deleteByExample(CmsClubExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsClubDO record);

    int insertSelective(CmsClubDO record);

    List<CmsClubDO> selectByExample(CmsClubExample example);

    CmsClubDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsClubDO record, @Param("example") CmsClubExample example);

    int updateByExample(@Param("record") CmsClubDO record, @Param("example") CmsClubExample example);

    int updateByPrimaryKeySelective(CmsClubDO record);

    int updateByPrimaryKey(CmsClubDO record);
}
