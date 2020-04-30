package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.core.dto.CmsClubsCertificationsQueryParam;
import edu.fzu.zhishe.core.dto.CmsClubsChiefChangeDTO;
import edu.fzu.zhishe.core.dto.CmsClubsChiefChangeQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsClubChiefChangeDAO {
    List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(@Param("queryParam")CmsClubsChiefChangeQueryParam cmsClubsChiefChangeQueryParam);
}
