package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsQuitDTO;
import edu.fzu.zhishe.core.param.CmsClubsQuitQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsClubQuitDAO {

    List<CmsClubsQuitDTO> listClubQuit(@Param("queryParam")CmsClubsQuitQueryParam cmsClubsQuitQueryParam,
                                       @Param("clubId")Integer clubId);
}
