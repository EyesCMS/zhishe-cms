package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandQueryParam;
import java.util.List;

import edu.fzu.zhishe.core.dto.CmsClubsDisbandDTO;
import org.apache.ibatis.annotations.Param;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public interface CmsClubDisbandDAO {
    List<CmsClubsDisbandDTO> listClubDisbandApply(@Param("queryParam") CmsClubsDisbandQueryParam queryParam);
}
