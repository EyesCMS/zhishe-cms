package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 5/17/2020.
 * @version 1.0
 */
public interface FmsUserLikePostDAO {

    int insertList(@Param("list") List<FmsUserLikePost> likeList);

    int truncateTable();
}
