package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandQueryParam;
import java.util.List;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandReturnParam;
import org.apache.ibatis.annotations.Param;

public interface CmsClubDisbandDAO {
    List<CmsClubsDisbandReturnParam> listClubDisbandApply(@Param("queryParam") CmsClubsDisbandQueryParam cmsClubsDisbandQueryParam);
}
