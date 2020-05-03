package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.mapper.FmsPostRemarkMapper;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.cms.model.FmsPostRemark;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.constant.PostTypeEnum;
import edu.fzu.zhishe.core.dao.FmsPostDAO;
import edu.fzu.zhishe.core.dao.FmsRemarkDAO;
import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.param.FmsPostParam;
import edu.fzu.zhishe.core.dto.FmsRemarkDTO;
import edu.fzu.zhishe.core.param.FmsRemarkParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.param.QueryParam;
import edu.fzu.zhishe.core.service.FmsForumService;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@Service
public class FmsForumServiceImpl implements FmsForumService {

    @Autowired
    private FmsPostDAO postDAO;

    @Autowired
    private FmsPostMapper postMapper;

    @Autowired
    private FmsRemarkDAO remarkDAO;

    @Autowired
    private FmsPostRemarkMapper remarkMapper;

    @Autowired
    private SysUserService userService;

    @Override
    public List<FmsPostDTO> listPersonalPost(Integer clubId, QueryParam queryParam) {
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        return postDAO.listPersonalPost(clubId, queryParam);
    }

    @Override
    public FmsPostDTO getPersonalPostById(Integer id) {
        return postDAO.getPersonalPostById(id);
    }

    @Override
    public List<FmsPostDTO> listActivityPost(Integer clubId, QueryParam queryParam) {
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        return postDAO.listActivityPost(clubId, queryParam);
    }

    @Override
    public FmsPostDTO getActivityPostById(Integer id) {
        return postDAO.getActivityPostById(id);
    }

    @Override
    public int savePost(FmsPostParam postParam) {
        SysUser currentUser = userService.getCurrentUser();
        FmsPost post = new FmsPost() {{
            setPosterId(currentUser.getId());
            setType(PostTypeEnum.PERSONAL.getValue());
            setTitle(postParam.getTitle());
            setContent(postParam.getContent());
            setCreateAt(new Date());
            setDeleteState(DeleteStateEnum.Existence.getValue());
        }};
        return postMapper.insertSelective(post);
    }

    @Override
    public int updatePost(Long id, FmsPostParam postParam) {
        FmsPost oldPost = postMapper.selectByPrimaryKey(id);
        Asserts.notNull(oldPost);
        if (oldPost.getDeleteState() == 1) {
            Asserts.notNull(null);
        }

        FmsPost post = new FmsPost() {{
            setId(id);
            setTitle(postParam.getTitle());
            setContent(postParam.getContent());
        }};
        return postMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public int deletePost(Long id) {
        FmsPost post = postMapper.selectByPrimaryKey(id);
        Asserts.notNull(post);
        if (post.getDeleteState() == 1) {
            Asserts.notNull(null);
        }

        FmsPost newPost = new FmsPost() {{
            setId(id);
            setDeleteState(1);
        }};
        return postMapper.updateByPrimaryKeySelective(newPost);
    }

    @Override
    public int saveRemark(FmsRemarkParam remarkParam) {
        Long postId = remarkParam.getPostId();
        FmsPost post = postMapper.selectByPrimaryKey(postId);
        Asserts.notNull(post);

        SysUser user = userService.getCurrentUser();
        FmsPostRemark remark = new FmsPostRemark() {{
            setUserId(user.getId());
            setPostId(remarkParam.getPostId().intValue());
            setContent(remarkParam.getContent());
            setCreateAt(new Date());
            setUpdateAt(null);
        }};
        return remarkMapper.insertSelective(remark);
    }

    @Override
    public int deleteRemark(Long id) {
        FmsPostRemark remark = remarkMapper.selectByPrimaryKey(id);
        Asserts.notNull(remark);

        Integer userId = userService.getCurrentUser().getId();
        if (!remark.getUserId().equals(userId)) {
            Asserts.notAuthorized();
        }
        return remarkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<FmsRemarkDTO> listRemarkByPostId(Long postId, PaginationParam paginationParam) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return remarkDAO.listRemarkByPostId(postId);
    }
}
