package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsQuitDTO;
import edu.fzu.zhishe.core.param.CmsClubsQuitQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsClubQuitDAO {

    List<CmsClubsQuitDTO> listClubQuit(@Param("queryParam") CmsClubsQuitQuery cmsClubsQuitQuery,
                                       @Param("clubId")Integer clubId);
}
