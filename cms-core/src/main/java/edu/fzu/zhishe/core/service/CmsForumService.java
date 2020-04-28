package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.core.dto.CmsActivityDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkParam;
import edu.fzu.zhishe.core.dto.QueryParam;
import java.util.List;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public interface CmsForumService {

    List<CmsActivityDTO> listPosts(Integer clubId, QueryParam queryParam);

    CmsActivityDTO getActivityById(Integer id);

    int deleteActivity(Integer id);

    int postRemark(CmsRemarkParam remarkParam);

    List<CmsRemarkDTO> listRemarkByPostId(Integer postId, QueryParam queryParam);

    long countRemarkByPostId(Integer postId);
}
