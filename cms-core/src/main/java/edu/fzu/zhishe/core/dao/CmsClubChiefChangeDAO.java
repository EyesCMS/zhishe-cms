package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsChiefChangeDTO;
import edu.fzu.zhishe.core.param.CmsClubsChiefChangeQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsClubChiefChangeDAO {
    List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(@Param("queryParam")CmsClubsChiefChangeQueryParam cmsClubsChiefChangeQueryParam);
}
