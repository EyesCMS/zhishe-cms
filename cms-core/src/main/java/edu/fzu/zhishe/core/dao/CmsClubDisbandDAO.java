package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandQueryParam;
import java.util.List;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandDTO;
import org.apache.ibatis.annotations.Param;

public interface CmsClubDisbandDAO {
    List<CmsClubsDisbandDTO> listClubDisbandApply(@Param("queryParam") CmsClubsDisbandQueryParam queryParam);
}
