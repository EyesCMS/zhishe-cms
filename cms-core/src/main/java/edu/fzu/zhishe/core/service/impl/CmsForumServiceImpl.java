package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsActivityMapper;
import edu.fzu.zhishe.cms.mapper.CmsUserActivityRemarkMapper;
import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.cms.model.CmsActivityExample;
import edu.fzu.zhishe.cms.model.CmsUserActivityRemark;
import edu.fzu.zhishe.cms.model.CmsUserActivityRemarkExample;
import edu.fzu.zhishe.core.dao.CmsActivityDAO;
import edu.fzu.zhishe.core.dto.CmsActivityDetails;
import edu.fzu.zhishe.core.dto.QueryParam;
import edu.fzu.zhishe.core.service.CmsForumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<CmsActivity> listPosts(Integer clubId, QueryParam queryParam) {
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());

        CmsActivityExample activityExample = new CmsActivityExample();
        activityExample.setOrderByClause(queryParam.getSort() + " " + queryParam.getOrder());
        if (clubId != null) {
            activityExample.createCriteria().andClubIdEqualTo(clubId);
        }
        if (!StringUtils.isEmpty(queryParam.getKeyword())) {
            activityExample.createCriteria().andTitleLike("%" + queryParam.getKeyword() + "%");
        }

        return activityMapper.selectByExample(activityExample);
    }

    @Override
    public CmsActivityDetails getActivityDetailById(Integer id) {
//        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
//
//        CmsUserActivityRemarkExample remarkExample = new CmsUserActivityRemarkExample();
//        remarkExample.createCriteria().andActivityIdEqualTo(id);
//        List<CmsUserActivityRemark> remarks = remarkMapper.selectByExample(remarkExample);
//
//        CmsActivityDetails activityDetails = new CmsActivityDetails();
        CmsActivityDetails activityDetails = activityDAO.getActivityDetailsById(id);
        return activityDetails;
    }
}
