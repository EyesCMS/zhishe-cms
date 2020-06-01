package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.mapper.FmsUserLikePostMapper;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import edu.fzu.zhishe.cms.model.FmsUserLikePostExample;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.LikedStatusEnum;
import edu.fzu.zhishe.core.dao.FmsUserLikePostDAO;
import edu.fzu.zhishe.core.dto.FmsLikedCountDTO;
import edu.fzu.zhishe.core.error.PostErrorEnum;
import edu.fzu.zhishe.core.service.FmsLikeCacheService;
import edu.fzu.zhishe.core.util.NotExistUtil;
import edu.fzu.zhishe.core.util.RedisKeyUtil;
import edu.fzu.zhishe.security.annotation.CacheException;
import edu.fzu.zhishe.security.service.RedisService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/31/2020
 */
@Service
public class FmsLikeCacheServiceImpl implements FmsLikeCacheService {

    public static final Logger LOGGER = LoggerFactory.getLogger(FmsLikeCacheServiceImpl.class);

    @Autowired
    RedisService redisService;
    @Autowired
    FmsUserLikePostMapper likePostMapper;
    @Autowired
    FmsUserLikePostDAO likePostDAO;
    @Autowired
    FmsPostMapper postMapper;

    public static final long LIKE_COUNT_DELTA = 1L;
    public static final long LIKE_EXPIRE_TIME = 3600 * 3L;

    /**
     * used to mark update posts
     * @param pid post id to mark
     */
    private void addUpdatedPostId(Long pid) {
        String key = RedisKeyUtil.getUpdatedPostLikeKey();
        redisService.sAdd(key, pid);
    }

    private void loadDataIfNotExist(String key, Long pid) {
        Long size = redisService.sSize(key);
        if (size == null || size == 0) {
            FmsUserLikePostExample example = new FmsUserLikePostExample();
            example.createCriteria().andPostIdEqualTo(pid);
            List<Integer> userIds = likePostMapper.selectByExample(example)
                .stream()
                .map(FmsUserLikePost::getUserId)
                .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(userIds)) {
                for (Integer userId : userIds) {
                    redisService.sAdd(key, userId);
                }
                redisService.expire(key, LIKE_EXPIRE_TIME);
            }
        }
    }

    @Override
    public Set<Object> listUpdatedPost() {

        String key = RedisKeyUtil.getUpdatedPostLikeKey();
        return redisService.sMembers(key);
    }

    @Override
    public boolean hasLiked(Integer uid, Long pid) {
        String key = RedisKeyUtil.getLikeKey(pid);
        this.loadDataIfNotExist(key, pid);
        return redisService.sIsMember(key, uid);
    }

    @Override
    public Integer getLikeCount(Long pid) {

        String key = RedisKeyUtil.getLikedCountKey();
        Object count = redisService.hGet(key, pid.toString());
        return (count == null) ? 0 : (Integer) count;
    }

    @Override
    @CacheException
    public void like(Integer uid, Long pid) {
        FmsPost post = postMapper.selectByPrimaryKey(pid);
        if (NotExistUtil.check(post)) {
            Asserts.notFound(PostErrorEnum.POST_NOT_EXIST);
        }
        String key = RedisKeyUtil.getLikeKey(pid);
        this.loadDataIfNotExist(key, pid);
        if (hasLiked(uid, pid)) {
            Asserts.fail(PostErrorEnum.ALREADY_LIKED);
        }
        redisService.sAdd(key, uid);
        incrLikedCount(pid);
        this.addUpdatedPostId(pid);
    }

    @Override
    @CacheException
    public void unlike(Integer uid, Long pid) {
        FmsPost post = postMapper.selectByPrimaryKey(pid);
        if (NotExistUtil.check(post)) {
            Asserts.notFound(PostErrorEnum.POST_NOT_EXIST);
        }
        if (!hasLiked(uid, pid)) {
            Asserts.fail(PostErrorEnum.DOES_NOT_LIKED);
        }
        String key = RedisKeyUtil.getLikeKey(pid);
        this.loadDataIfNotExist(key, pid);
        redisService.sRemove(key, uid);
        decrLikedCount(pid);
        this.addUpdatedPostId(pid);
    }

    @Override
    public void incrLikedCount(Long pid) {

        String key = RedisKeyUtil.getLikedCountKey();
        redisService.hIncr(key, pid.toString(), LIKE_COUNT_DELTA);
    }

    @Override
    public void decrLikedCount(Long pid) {

        String key = RedisKeyUtil.getLikedCountKey();
        redisService.hDecr(key, pid.toString(), LIKE_COUNT_DELTA);
    }

    @Override
    public void deletePostLikeSet(Long postId) {
        redisService.del(RedisKeyUtil.getLikeKey(postId));
    }

    @Override
    public List<FmsUserLikePost> listLikedData() {

        List<FmsUserLikePost> result = new ArrayList<>();
        Set<Object> objects = listUpdatedPost();
        for (Object object : objects) {
            long postId = ((Integer) object).longValue();
            Set<Object> userIds = redisService.sMembers(RedisKeyUtil.getLikeKey(postId));
            for (Object uid : userIds) {
                FmsUserLikePost userLikePost = new FmsUserLikePost();
                userLikePost.setPostId(postId);
                userLikePost.setUserId((Integer) uid);
                userLikePost.setStatus(LikedStatusEnum.LIKE.getCode());
                result.add(userLikePost);
            }
        }
        return result;
    }

    @Override
    public List<FmsLikedCountDTO> listLikedCount() {

        List<FmsLikedCountDTO> likePostList = new ArrayList<>();
        Map<Object, Object> entries = redisService.hGetAll(RedisKeyUtil.getLikedCountKey());
        entries.forEach((k, v) -> {
            String key = (String) k;
            FmsLikedCountDTO likedCountDTO = new FmsLikedCountDTO();
            likedCountDTO.setId(Long.valueOf(key));
            likedCountDTO.setCount((Integer) v);
            likePostList.add(likedCountDTO);

            // 存到 list 后从 redis 删除
            // redisService.hDel(constKey, key);
        });
        return likePostList;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void syncLikeDataToDatabase() {

        // 1. like data
        int count = 0;
        Set<Object> objects = listUpdatedPost();
        for (Object object : objects) {
            long postId = ((Integer) object).longValue();
            Set<Object> userIds = redisService.sMembers(RedisKeyUtil.getLikeKey(postId));
            List<FmsUserLikePost> userLikePosts = new ArrayList<>();
            for (Object uid : userIds) {
                FmsUserLikePost userLikePost = new FmsUserLikePost();
                userLikePost.setPostId(postId);
                userLikePost.setUserId((Integer) uid);
                userLikePost.setStatus(LikedStatusEnum.LIKE.getCode());
                userLikePosts.add(userLikePost);
            }
            // remove old db data
            FmsUserLikePostExample example = new FmsUserLikePostExample();
            example.createCriteria().andPostIdEqualTo(postId);
            // save new data to db
            count += likePostDAO.insertList(userLikePosts);
        }
        LOGGER.info("update {} like post records from redis to db", count);

        // 2. like count
        count = 0;
        List<FmsLikedCountDTO> likedCountDTOList = listLikedCount();
        FmsPost newPost = new FmsPost();
        for (FmsLikedCountDTO redisRecord : likedCountDTOList) {
            FmsPost post = postMapper.selectByPrimaryKey(redisRecord.getId());
            // 点赞数量为无关紧要操作 -- 出错采用鸵鸟策略
            if (!NotExistUtil.check(post)) {
                Integer likeCount = redisRecord.getCount();
                newPost.setId(post.getId());
                newPost.setLikeCount(likeCount);
                count += postMapper.updateByPrimaryKeySelective(newPost);
            }
        }
        LOGGER.info("update {} like post count from redis to db", count);

        // 3. clear updated post set
        boolean result = redisService.del(RedisKeyUtil.getUpdatedPostLikeKey());
        LOGGER.info("clear updated post list result: {}", result);
    }
}
