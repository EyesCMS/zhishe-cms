package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsUserClubRelMapper {
    long countByExample(CmsUserClubRelExample example);

    int deleteByExample(CmsUserClubRelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsUserClubRel record);

    int insertSelective(CmsUserClubRel record);

    List<CmsUserClubRel> selectByExample(CmsUserClubRelExample example);

    CmsUserClubRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsUserClubRel record, @Param("example") CmsUserClubRelExample example);

    int updateByExample(@Param("record") CmsUserClubRel record, @Param("example") CmsUserClubRelExample example);

    int updateByPrimaryKeySelective(CmsUserClubRel record);

    int updateByPrimaryKey(CmsUserClubRel record);
}