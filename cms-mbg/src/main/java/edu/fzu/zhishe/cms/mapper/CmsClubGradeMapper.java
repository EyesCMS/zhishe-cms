package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsClubGrade;
import edu.fzu.zhishe.cms.model.CmsClubGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubGradeMapper {
    long countByExample(CmsClubGradeExample example);

    int deleteByExample(CmsClubGradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsClubGrade record);

    int insertSelective(CmsClubGrade record);

    List<CmsClubGrade> selectByExample(CmsClubGradeExample example);

    CmsClubGrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsClubGrade record, @Param("example") CmsClubGradeExample example);

    int updateByExample(@Param("record") CmsClubGrade record, @Param("example") CmsClubGradeExample example);

    int updateByPrimaryKeySelective(CmsClubGrade record);

    int updateByPrimaryKey(CmsClubGrade record);
}