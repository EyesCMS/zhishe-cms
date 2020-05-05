package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.mapper.FmsPostRemarkMapper;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.cms.model.FmsPostRemark;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
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
import javax.swing.text.html.parser.Entity;
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
            setImgUrl(postParam.getImgUrl());
            setCreateAt(new Date());
            setDeleteState(DeleteStateEnum.Existence.getValue());
        }};
        return postMapper.insertSelective(post);
    }

    @Override
    public int updatePost(Long id, FmsPostParam postParam) {
        FmsPost oldPost = postMapper.selectByPrimaryKey(id);
        Asserts.notFound(oldPost == null || oldPost.getDeleteState() == 1);

        if (oldPost.getType().equals(PostTypeEnum.ACTIVITY.getValue())) {
            Asserts.fail("can't update activity post");
        }
        SysUser currentUser = userService.getCurrentUser();
        if (!oldPost.getPosterId().equals(currentUser.getId())) {
            Asserts.forbidden();
        }

        FmsPost post = new FmsPost() {{
            setId(id);
            setTitle(postParam.getTitle());
            setContent(postParam.getContent());
            setImgUrl(postParam.getImgUrl());
        }};
        return postMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public int deletePost(Long id) {
        SysUser currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }

        FmsPost post = postMapper.selectByPrimaryKey(id);
        Asserts.notFound(post == null || post.getDeleteState() == 1);

        if (post.getType().equals(PostTypeEnum.ACTIVITY.getValue())) {
            Asserts.fail("can't delete activity post");
        }
        if (!currentUser.getId().equals(post.getPosterId())) {
            Asserts.forbidden();
        }

        FmsPost newPost = new FmsPost() {{
            setId(id);
            setDeleteState(1);
        }};
        return postMapper.updateByPrimaryKeySelective(newPost);
    }

    @Override
    public int saveRemark(FmsRemarkParam remarkParam) {
        SysUser currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }

        Long postId = remarkParam.getPostId();
        FmsPost post = postMapper.selectByPrimaryKey(postId);
        if (post == null || post.getDeleteState() == 1) {
            Asserts.notFound();
        }

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
            Asserts.unAuthorized();
        }
        return remarkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<FmsRemarkDTO> listRemarkByPostId(Long postId, PaginationParam paginationParam) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return remarkDAO.listRemarkByPostId(postId);
    }
}
