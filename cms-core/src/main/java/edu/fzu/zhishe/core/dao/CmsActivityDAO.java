package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsActivityDTO;
import edu.fzu.zhishe.core.dto.CmsActivityDetails;
import edu.fzu.zhishe.core.dto.QueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 4/26/2020.
 * @version 1.0
 */
public interface CmsActivityDAO {

    List<CmsActivityDTO> listActivity(@Param("clubId") Integer clubId,
        @Param("queryParam") QueryParam queryParam);

    CmsActivityDTO getActivityById(@Param("id") Integer id);
}
