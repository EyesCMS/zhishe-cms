package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsRemarkDTO;
import edu.fzu.zhishe.core.dto.QueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 4/28/2020.
 * @version 1.0
 */
public interface CmsRemarkDAO {

    List<CmsRemarkDTO> listRemarkByPostId(@Param("postId") Integer postId, @Param("queryParam") QueryParam queryParam);
}
