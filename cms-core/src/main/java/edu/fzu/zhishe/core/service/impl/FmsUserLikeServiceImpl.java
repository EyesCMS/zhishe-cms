package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.mapper.FmsUserLikePostMapper;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import edu.fzu.zhishe.cms.model.FmsUserLikePostExample;
import edu.fzu.zhishe.core.dao.FmsUserLikePostDAO;
import edu.fzu.zhishe.core.dto.FmsLikedCountDTO;
import edu.fzu.zhishe.core.service.FmsLikeCacheService;
import edu.fzu.zhishe.core.service.FmsUserLikeService;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.List;
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
        if (likePostList == null || likePostList.isEmpty()) {
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
        if (!CollectionUtil.isEmpty(likePostList)) {
            userLikePostDAO.insertList(likePostList);
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
