package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.FmsRemarkDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 5/1/2020.
 * @version 1.0
 */
public interface FmsRemarkDAO {

    List<FmsRemarkDTO> listRemarkByPostId(@Param("postId") Long postId);
}
