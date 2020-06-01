package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsClubPicture;
import edu.fzu.zhishe.cms.model.CmsClubPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubPictureMapper {
    long countByExample(CmsClubPictureExample example);

    int deleteByExample(CmsClubPictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsClubPicture record);

    int insertSelective(CmsClubPicture record);

    List<CmsClubPicture> selectByExample(CmsClubPictureExample example);

    CmsClubPicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsClubPicture record, @Param("example") CmsClubPictureExample example);

    int updateByExample(@Param("record") CmsClubPicture record, @Param("example") CmsClubPictureExample example);

    int updateByPrimaryKeySelective(CmsClubPicture record);

    int updateByPrimaryKey(CmsClubPicture record);
}