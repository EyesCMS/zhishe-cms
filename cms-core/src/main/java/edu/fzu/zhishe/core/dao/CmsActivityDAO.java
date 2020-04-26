package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsActivityDetails;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 4/26/2020.
 * @version 1.0
 */
public interface CmsActivityDAO {

    CmsActivityDetails getActivityDetailsById(@Param("id") Integer id);
}
