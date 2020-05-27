package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.mapper.FmsPostRemarkMapper;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.cms.model.FmsPostRemark;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.IsLogin;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.constant.PostTypeEnum;
import edu.fzu.zhishe.core.dao.FmsPostDAO;
import edu.fzu.zhishe.core.dao.FmsRemarkDAO;
import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.error.PostErrorEnum;
import edu.fzu.zhishe.core.param.FmsPostParam;
import edu.fzu.zhishe.core.dto.FmsRemarkDTO;
import edu.fzu.zhishe.core.param.FmsPostQuery;
import edu.fzu.zhishe.core.param.FmsRemarkParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.FmsForumService;
import edu.fzu.zhishe.core.service.FmsUserLikeService;
import edu.fzu.zhishe.core.service.StorageService;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.core.util.NotExistUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FmsForumServiceImpl implements FmsForumService {

    @Autowired
    private FmsPostDAO postDAO;

    @Autowired
    private FmsPostMapper postMapper;

    @Autowired
    StorageService storageService;

    @Autowired
    private FmsRemarkDAO remarkDAO;

    @Autowired
    private FmsPostRemarkMapper remarkMapper;

    @Autowired
    private SysUserService userService;

    @Autowired
    FmsUserLikeService userLikeService;

    private FmsPostDTO queryLikeCount(FmsPostDTO post) {
        if (post != null) {
            post.setLikeCount(userLikeService.getPostLikeCount(post.getId()));
        }
        return post;
    }

    private List<FmsPostDTO> queryLikeCount(List<FmsPostDTO> postList) {
        postList.forEach(p -> {
            p.setLikeCount(userLikeService.getPostLikeCount(p.getId()));
        });
        return postList;
    }

    @Override
    public List<FmsPostDTO> listPersonalPost(Integer clubId, PaginationParam paginationParam, FmsPostQuery postQuery) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return queryLikeCount(postDAO.listPersonalPost(clubId, postQuery));
    }

    @Override
    public FmsPostDTO getPersonalPostById(Integer id) {
        return queryLikeCount(postDAO.getPersonalPostById(id));
    }

    @Override
    public List<FmsPostDTO> listActivityPost(Integer clubId, PaginationParam paginationParam, FmsPostQuery postQuery) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return queryLikeCount(postDAO.listActivityPost(clubId, postQuery));
    }

    @Override
    public FmsPostDTO getActivityPostById(Integer id) {
        return queryLikeCount(postDAO.getActivityPostById(id));
    }

    @IsLogin
    @Override
    public int savePost(FmsPostParam postParam, MultipartFile imageFile) {

        String imgUrl = storageService.storeImage(imageFile);
        FmsPost post = new FmsPost();
        post.setPosterId(userService.getCurrentUser().getId());
        post.setType(PostTypeEnum.PERSONAL.getValue());
        post.setTitle(postParam.getTitle());
        post.setContent(postParam.getContent());
        post.setImgUrl(imgUrl);
        post.setCreateAt(new Date());
        post.setDeleteState(DeleteStateEnum.Existence.getValue());
        return postMapper.insertSelective(post);
    }

    @IsLogin
    @Override
    public int updatePost(Long id, FmsPostParam postParam) {
        FmsPost oldPost = postMapper.selectByPrimaryKey(id);
        if (oldPost == null || oldPost.getDeleteState() == DeleteStateEnum.Deleted.getValue()) {
            Asserts.notFound(PostErrorEnum.POST_NOT_EXIST);
        }

        if (oldPost.getType().equals(PostTypeEnum.ACTIVITY.getValue())) {
            Asserts.fail(PostErrorEnum.CAN_NOT_UPDATE_ACTIVITY_POST);
        }
        SysUser currentUser = userService.getCurrentUser();
        if (!oldPost.getPosterId().equals(currentUser.getId())) {
            Asserts.forbidden(PostErrorEnum.NOT_POSTER);
        }

        FmsPost post = new FmsPost();
        post.setId(id);
        post.setTitle(postParam.getTitle());
        post.setContent(postParam.getContent());
        return postMapper.updateByPrimaryKeySelective(post);
    }

    @IsLogin
    @Override
    public int deletePost(Long id) {

        FmsPost post = postMapper.selectByPrimaryKey(id);
        if (NotExistUtil.check(post)) {
            Asserts.notFound(PostErrorEnum.POST_NOT_EXIST);
        }

        if (post.getType().equals(PostTypeEnum.ACTIVITY.getValue())) {
            Asserts.fail(PostErrorEnum.CAN_NOT_DELETE_ACTIVITY_POST);
        }
        if (!userService.getCurrentUser().getId().equals(post.getPosterId())) {
            Asserts.forbidden(PostErrorEnum.NOT_POSTER);
        }

        FmsPost newPost = new FmsPost();
        newPost.setId(id);
        newPost.setDeleteState(DeleteStateEnum.Deleted.getValue());
        return postMapper.updateByPrimaryKeySelective(newPost);
    }

    @IsLogin
    @Override
    public int saveRemark(FmsRemarkParam remarkParam) {

        Long postId = remarkParam.getPostId();
        FmsPost post = postMapper.selectByPrimaryKey(postId);
        if (NotExistUtil.check(post)) {
            Asserts.notFound(PostErrorEnum.POST_NOT_EXIST);
        }

        FmsPostRemark remark = new FmsPostRemark();
        remark.setUserId(userService.getCurrentUser().getId());
        remark.setPostId(remarkParam.getPostId().intValue());
        remark.setContent(remarkParam.getContent());
        remark.setCreateAt(new Date());
        remark.setUpdateAt(null);
        return remarkMapper.insertSelective(remark);
    }

    @IsLogin
    @Override
    public int deleteRemark(Long id) {
        FmsPostRemark remark = remarkMapper.selectByPrimaryKey(id);
        if (remark == null) {
            Asserts.notFound(PostErrorEnum.REMARK_NOT_EXIST);
        }

        Integer userId = userService.getCurrentUser().getId();
        if (!remark.getUserId().equals(userId)) {
            Asserts.forbidden(PostErrorEnum.NOT_POSTER);
        }
        return remarkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<FmsRemarkDTO> listRemarkByPostId(Long postId, PaginationParam paginationParam) {
        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return remarkDAO.listRemarkByPostId(postId);
    }
}
