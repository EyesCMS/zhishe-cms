package edu.fzu.zhishe.cms.mapper;

import edu.fzu.zhishe.cms.model.CmsQuitNotice;
import edu.fzu.zhishe.cms.model.CmsQuitNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsQuitNoticeMapper {
    long countByExample(CmsQuitNoticeExample example);

    int deleteByExample(CmsQuitNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsQuitNotice record);

    int insertSelective(CmsQuitNotice record);

    List<CmsQuitNotice> selectByExample(CmsQuitNoticeExample example);

    CmsQuitNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsQuitNotice record, @Param("example") CmsQuitNoticeExample example);

    int updateByExample(@Param("record") CmsQuitNotice record, @Param("example") CmsQuitNoticeExample example);

    int updateByPrimaryKeySelective(CmsQuitNotice record);

    int updateByPrimaryKey(CmsQuitNotice record);
}