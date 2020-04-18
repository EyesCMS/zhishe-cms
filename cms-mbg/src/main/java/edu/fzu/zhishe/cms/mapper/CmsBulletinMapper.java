package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.cms.model.CmsBulletinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsBulletinMapper {
    long countByExample(CmsBulletinExample example);

    int deleteByExample(CmsBulletinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsBulletin record);

    int insertSelective(CmsBulletin record);

    List<CmsBulletin> selectByExample(CmsBulletinExample example);

    CmsBulletin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsBulletin record, @Param("example") CmsBulletinExample example);

    int updateByExample(@Param("record") CmsBulletin record, @Param("example") CmsBulletinExample example);

    int updateByPrimaryKeySelective(CmsBulletin record);

    int updateByPrimaryKey(CmsBulletin record);
}