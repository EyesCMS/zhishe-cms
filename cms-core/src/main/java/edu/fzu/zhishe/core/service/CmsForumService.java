package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.core.dto.CmsActivityDetails;
import edu.fzu.zhishe.core.dto.QueryParam;
import java.util.List;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public interface CmsForumService {

    List<CmsActivity> listPosts(Integer clubId, QueryParam queryParam);

    CmsActivityDetails getActivityDetailById(Integer id);
}
