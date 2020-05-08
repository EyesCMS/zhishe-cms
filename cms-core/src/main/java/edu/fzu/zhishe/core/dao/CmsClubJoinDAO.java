package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsClubsJoinDTO;
import edu.fzu.zhishe.core.param.CmsClubsJoinQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public interface CmsClubJoinDAO {
    List<CmsClubsJoinDTO> listClubJoinApply(@Param("queryParam") CmsClubsJoinQuery queryParam,
                                            @Param("clubId") Integer clubId);
}
