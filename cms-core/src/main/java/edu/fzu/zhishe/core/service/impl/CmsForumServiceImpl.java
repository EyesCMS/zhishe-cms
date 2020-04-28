package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.CmsActivityMapper;
import edu.fzu.zhishe.cms.mapper.CmsUserActivityRemarkMapper;
import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.core.constant.ActivityStateEnum;
import edu.fzu.zhishe.core.dao.CmsActivityDAO;
import edu.fzu.zhishe.core.dto.CmsActivityDTO;
import edu.fzu.zhishe.core.dto.QueryParam;
import edu.fzu.zhishe.core.service.CmsForumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@Service
public class CmsForumServiceImpl implements CmsForumService {

    @Autowired
    private CmsActivityMapper activityMapper;

    @Autowired
    private CmsActivityDAO activityDAO;

    @Autowired
    private CmsUserActivityRemarkMapper remarkMapper;

    @Override
    public List<CmsActivityDTO> listPosts(Integer clubId, QueryParam queryParam) {
        return activityDAO.listActivity(clubId, queryParam);
    }

    @Override
    public CmsActivityDTO getActivityById(Integer id) {
        return activityDAO.getActivityById(id);
    }

    @Override
    public void deleteActivity(Integer id) {
        CmsActivity cmsActivity = activityMapper.selectByPrimaryKey(id);
        cmsActivity.setState(ActivityStateEnum.FINISHED.getValue());
        activityMapper.updateByPrimaryKey(cmsActivity);
    }
}
