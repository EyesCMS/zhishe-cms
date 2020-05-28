package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.mapper.FmsUserLikePostMapper;
import edu.fzu.zhishe.cms.mapper.SysUserMapper;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import edu.fzu.zhishe.cms.model.FmsUserLikePostExample;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.IsLogin;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.constant.LikedStatusEnum;
import edu.fzu.zhishe.core.dao.FmsUserLikePostDAO;
import edu.fzu.zhishe.core.dto.FmsLikedCountDTO;
import edu.fzu.zhishe.core.error.PostErrorEnum;
import edu.fzu.zhishe.core.service.FmsLikeCacheService;
import edu.fzu.zhishe.core.service.FmsUserLikeService;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.core.util.NotExistUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liang on 5/15/2020.
 * @version 1.0
 */
@Service
public class FmsUserLikeServiceImpl implements FmsUserLikeService {

    @Autowired
    FmsUserLikePostMapper userLikePostMapper;

    @Autowired
    FmsUserLikePostDAO userLikePostDAO;

    @Autowired
    FmsLikeCacheService likeCacheService;

    @Autowired
    SysUserService userService;

    @Autowired
    FmsPostMapper postMapper;

    @Autowired
    SysUserMapper userMapper;

    @IsLogin
    @Override
    public Integer getLikeStatus(Long postId) {

        SysUser currentUser = userService.getCurrentUser();
        return likeCacheService.hasLiked(currentUser.getId(), postId)
            ? LikedStatusEnum.LIKE.getCode()
            : LikedStatusEnum.UNLIKE.getCode();
    }

    @IsLogin
    @Override
    public void like(Long likedPostId) {
        FmsPost post = postMapper.selectByPrimaryKey(likedPostId);
        if (NotExistUtil.check(post)) {
            Asserts.notFound(PostErrorEnum.POST_NOT_EXIST);
        }

        SysUser currentUser = userService.getCurrentUser();
        if (likeCacheService.hasLiked(currentUser.getId(), likedPostId)) {
            Asserts.fail(PostErrorEnum.ALREADY_LIKED);
        }

        // 先存到 Redis 里面，再定时写到数据库里
        likeCacheService.saveLiked2Redis(currentUser.getId(), likedPostId);
        likeCacheService.incrLikedCount(likedPostId);
    }

    @IsLogin
    @Override
    public void unlike(Long likedPostId) {

        FmsPost post = postMapper.selectByPrimaryKey(likedPostId);
        if (NotExistUtil.check(post)) {
            Asserts.notFound(PostErrorEnum.POST_NOT_EXIST);
        }

        SysUser currentUser = userService.getCurrentUser();
        if (likeCacheService.hasUnLiked(currentUser.getId(), likedPostId)) {
            Asserts.fail(PostErrorEnum.DOES_NOT_LIKED);
        }
        // 取消点赞，先存到 Redis 里面，再定时写到数据库里
        likeCacheService.unlikeFromRedis(currentUser.getId(), likedPostId);
        likeCacheService.decrLikedCount(likedPostId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int save(FmsUserLikePost record) {

        return userLikePostMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int saveAll(List<FmsUserLikePost> records) {

        return userLikePostDAO.insertList(records);
    }

    @Override
    public Integer getPostLikeCount(Long postId) {
        return likeCacheService.getLikeCount(postId);
    }

    @Override
    public List<FmsUserLikePost> listLikedListByPostId(Long postId) {

        FmsUserLikePostExample example = new FmsUserLikePostExample();
        example.createCriteria().andPostIdEqualTo(postId);
        return userLikePostMapper.selectByExample(example);
    }

    @Override
    public List<FmsUserLikePost> listLikedListByUserId(Integer userId) {

        FmsUserLikePostExample example = new FmsUserLikePostExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userLikePostMapper.selectByExample(example);
    }

    @Override
    public FmsUserLikePost getByLikedUserIdAndLikedPostId(Integer likedUserId, Long likedPostId) {

        FmsUserLikePostExample example = new FmsUserLikePostExample();
        example.createCriteria().andUserIdEqualTo(likedUserId)
            .andPostIdEqualTo(likedPostId);
        List<FmsUserLikePost> likePostList = userLikePostMapper.selectByExample(example);
        if (CollUtil.isEmpty(likePostList)) {
            return null;
        }
        return likePostList.get(0);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void transLikedFromRedis2DB() {

        // 1. truncate all data from table
        userLikePostDAO.truncateTable();

        // 2. insert all data from Redis to MySQL
        List<FmsUserLikePost> likePostList = likeCacheService.listLikedDataFromRedis();
        List<FmsUserLikePost> checkedList = likePostList.stream()
            .filter(p -> {
                SysUser user = userMapper.selectByPrimaryKey(p.getUserId());
                if (user == null) {
                    return false;
                }
                FmsPost post = postMapper.selectByPrimaryKey(p.getPostId());
                return !NotExistUtil.check(post);
            }).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(checkedList)) {
            userLikePostDAO.insertList(checkedList);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void transLikedCountFromRedis2DB() {

        List<FmsLikedCountDTO> likePostList = likeCacheService.listLikedCountFromRedis();
        FmsPost newPost = new FmsPost();
        for (FmsLikedCountDTO redisRecord : likePostList) {
            FmsPost post = postMapper.selectByPrimaryKey(redisRecord.getId());
            // 点赞数量为无关紧要操作 -- 出错采用鸵鸟策略
            if (post != null) {
                Integer likeCount = redisRecord.getCount();
                newPost.setId(post.getId());
                newPost.setLikeCount(likeCount);
                postMapper.updateByPrimaryKeySelective(newPost);
            }
        }
    }
}
