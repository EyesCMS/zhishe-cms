package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandQueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubDisbandDAO {
    List<CmsClubsDisbandQueryParam> listClubDisbandApply(@Param("queryParam") CmsClubsDisbandQueryParam cmsClubsDisbandQueryParam);
}
