package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.param.QueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 5/1/2020.
 * @version 1.0
 */
public interface FmsPostDAO {

    List<FmsPostDTO> listActivityPost(@Param("clubId") Integer clubId, @Param("queryParam") QueryParam queryParam);

    FmsPostDTO getActivityPostById(@Param("id") Integer id);

    List<FmsPostDTO> listPersonalPost(@Param("clubId") Integer clubId, @Param("queryParam") QueryParam queryParam);

    FmsPostDTO getPersonalPostById(@Param("id") Integer id);
}
