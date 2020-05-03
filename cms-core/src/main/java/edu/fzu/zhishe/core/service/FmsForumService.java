package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.core.param.FmsPostParam;
import edu.fzu.zhishe.core.dto.FmsRemarkDTO;
import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.param.FmsRemarkParam;
import edu.fzu.zhishe.core.param.QueryParam;
import java.util.List;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public interface FmsForumService {

    List<FmsPostDTO> listPersonalPost(Integer clubId, QueryParam queryParam);

    FmsPostDTO getPersonalPostById(Integer id);

    List<FmsPostDTO> listActivityPost(Integer clubId, QueryParam queryParam);

    FmsPostDTO getActivityPostById(Integer id);

    int savePost(FmsPostParam postParam);

    int updatePost(Long id, FmsPostParam postParam);

    int deletePost(Long id);

    int saveRemark(FmsRemarkParam remarkParam);

    int deleteRemark(Long id);

    List<FmsRemarkDTO> listRemarkByPostId(Long postId, int page, int limit);
}
