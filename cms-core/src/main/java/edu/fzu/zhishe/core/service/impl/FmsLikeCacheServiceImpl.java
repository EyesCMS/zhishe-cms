package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import edu.fzu.zhishe.core.constant.LikedStatusEnum;
import edu.fzu.zhishe.core.dto.FmsLikedCountDTO;
import edu.fzu.zhishe.core.service.FmsLikeCacheService;
import edu.fzu.zhishe.core.util.RedisKeyUtils;
import edu.fzu.zhishe.security.service.RedisService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author liang on 5/15/2020.
 * @version 1.0
 */
@Service
public class FmsLikeCacheServiceImpl implements FmsLikeCacheService {

    @Autowired
    RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.key.post.liked}")
    private String REDIS_KEY_POST_LIKED;

    @Value("${redis.key.post.liked-count}")
    private String REDIS_KEY_POST_LIKED_COUNT;


    @Override
    public boolean hasLiked(Integer likedUserId, Long likedPostId) {

        String key = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED;
        String likedKey = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        if (redisService.hHasKey(key, likedKey)) {
            Integer value = (Integer) redisService.hGet(key, likedKey);
            return value.equals(LikedStatusEnum.LIKE.getCode());
        }
        return false;
    }

    @Override
    public boolean hasUnLiked(Integer likedUserId, Long likedPostId) {

        String key = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED;
        String likedKey = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        if (redisService.hHasKey(key, likedKey)) {
            Integer value = (Integer) redisService.hGet(key, likedKey);
            return value.equals(LikedStatusEnum.UNLIKE.getCode());
        }
        return false;
    }

    @Override
    public void saveLiked2Redis(Integer likedUserId, Long likedPostId) {

        String key = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED;
        String likedKey = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisService.hSet(key, likedKey, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(Integer likedUserId, Long likedPostId) {

        String key = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED;
        String likedKey = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisService.hSet(key, likedKey, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(Integer likedUserId, Long likedPostId) {

        String key = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED;
        String likedKey = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisService.hDel(key, likedKey);
    }

    @Override
    public void incrLikedCount(Long likedPostId) {

        String key = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED_COUNT;
        redisService.hIncr(key, likedPostId.toString(), 1L);
    }

    @Override
    public void decrLikedCount(Long likedPostId) {

        String key = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED_COUNT;
        redisService.hDecr(key, likedPostId.toString(), 1L);
    }

    @Override
    public List<FmsUserLikePost> listLikedDataFromRedis() {

        String constKey = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED;

        Map<Object, Object> entries = redisService.hGetAll(constKey);
        List<FmsUserLikePost> likePostList = new ArrayList<>();
        entries.forEach((k, v) -> {
            String key = (String) k;
            String[] tokens = key.split("::");
            Integer likedUserId = Integer.valueOf(tokens[0]);
            Long likedPostId = Long.valueOf(tokens[1]);
            Integer status = (Integer) v;
            if (status.equals(LikedStatusEnum.LIKE.getCode())) {
                // 组装成 FmsUserLikePost 对象
                FmsUserLikePost fmsUserLikePost = new FmsUserLikePost() {{
                    setUserId(likedUserId);
                    setPostId(likedPostId);
                    setStatus(status);
                }};
                likePostList.add(fmsUserLikePost);
            } else {
                // 删除取消点赞的记录
                redisService.hDel(constKey, key);
            }

            // 存到 list 后从 redis 删除
            //redisService.hDel(constKey, key);
        });

        return likePostList;
    }

    @Override
    public List<FmsLikedCountDTO> listLikedCountFromRedis() {

        String constKey = REDIS_DATABASE + ":" + REDIS_KEY_POST_LIKED_COUNT;
        List<FmsLikedCountDTO> likePostList = new ArrayList<>();
        Map<Object, Object> entries = redisService.hGetAll(constKey);
        entries.forEach((k, v) -> {
            String key = (String) k;
            FmsLikedCountDTO likedCountDTO = new FmsLikedCountDTO() {{
                setId(Long.valueOf(key));
                setCount((Integer) v);
            }};
            likePostList.add(likedCountDTO);

            // 存到 list 后从 redis 删除
            // redisService.hDel(constKey, key);
        });
        return likePostList;
    }
}
