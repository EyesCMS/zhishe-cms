package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandReturnParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsClubDisbandDAO {
    List<CmsClubsDisbandReturnParam> listClubDisbandApply(@Param("queryParam") CmsClubsDisbandReturnParam cmsClubsDisbandReturnParam);
}
