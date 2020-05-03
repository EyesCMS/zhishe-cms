package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsCertificationsDTO;
import edu.fzu.zhishe.core.param.CmsClubsCertificationsQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsClubCertificationDAO {
    List<CmsClubsCertificationsDTO> listClubCertificationApply(@Param("queryParam")CmsClubsCertificationsQueryParam cmsClubsCertificationsQueryParam);
}
