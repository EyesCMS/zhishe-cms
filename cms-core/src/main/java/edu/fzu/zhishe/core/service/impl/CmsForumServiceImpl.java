package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsActivityMapper;
import edu.fzu.zhishe.cms.mapper.CmsUserActivityRemarkMapper;
import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.cms.model.CmsUserActivityRemark;
import edu.fzu.zhishe.cms.model.CmsUserActivityRemarkExample;
import edu.fzu.zhishe.core.constant.ActivityStateEnum;
import edu.fzu.zhishe.core.dao.CmsActivityDAO;
import edu.fzu.zhishe.core.dao.CmsRemarkDAO;
import edu.fzu.zhishe.core.dto.CmsPostDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkParam;
import edu.fzu.zhishe.core.dto.QueryParam;
import edu.fzu.zhishe.core.service.CmsForumService;
import java.util.Date;
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
    private CmsActivityDAO activityDAO;

    @Autowired
    private CmsUserActivityRemarkMapper remarkMapper;

    @Autowired
    private CmsRemarkDAO remarkDAO;

    @Override
    public List<CmsPostDTO> listActivityPost(Integer clubId, QueryParam queryParam) {
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        return activityDAO.listActivityPost(clubId, queryParam);
    }

    @Override
    public CmsPostDTO getActivityPostById(Integer id) {
        return activityDAO.getActivityPostById(id);
    }

    @Override
    public int saveRemark(CmsRemarkParam remarkParam) {
        CmsUserActivityRemark userActivityRemark = new CmsUserActivityRemark() {{
            setUserId(remarkParam.getUid());
            setActivityId(remarkParam.getPid());
            setContent(remarkParam.getContent());
            setCreateAt(new Date());
            setUpdateAt(null);
        }};
        return remarkMapper.insertSelective(userActivityRemark);
    }

    @Override
    public List<CmsRemarkDTO> listRemarkByPostId(Integer postId, QueryParam queryParam) {
        return remarkDAO.listRemarkByPostId(postId,queryParam);
    }

    @Override
    public long countRemarkByPostId(Integer postId) {
        CmsUserActivityRemarkExample remarkExample = new CmsUserActivityRemarkExample();
        remarkExample.createCriteria().andActivityIdEqualTo(postId);
        return remarkMapper.countByExample(remarkExample);
    }
}
