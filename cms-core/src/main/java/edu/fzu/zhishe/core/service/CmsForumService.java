package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.core.dto.CmsPostDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkParam;
import edu.fzu.zhishe.core.dto.QueryParam;
import java.util.List;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public interface CmsForumService {

    // TODO
    //List<CmsPostDTO> listPersonalPost(QueryParam queryParam);
    // TODO
    //CmsPostDTO getPersonalPostById(Integer id);

    List<CmsPostDTO> listActivityPost(Integer clubId, QueryParam queryParam);

    CmsPostDTO getActivityPostById(Integer id);

    int saveRemark(CmsRemarkParam remarkParam);

    List<CmsRemarkDTO> listRemarkByPostId(Integer postId, QueryParam queryParam);

    long countRemarkByPostId(Integer postId);
}
