package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsDTO;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsQueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 4/28/2020.
 * @version 1.0
 */
public interface CmsClubCreationDAO {

    List<CmsClubsCreationsDTO> listClubCreationApply(@Param("queryParam") CmsClubsCreationsQueryParam queryParam);
}
