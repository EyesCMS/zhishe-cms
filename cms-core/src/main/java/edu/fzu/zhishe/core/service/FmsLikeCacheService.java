package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import edu.fzu.zhishe.core.dto.FmsLikedCountDTO;
import java.util.List;

/**
 * 点赞缓存操作类
 *
 * @author liang
 * @date 2020/5/15
 */
public interface FmsLikeCacheService {

    /**
     * 已经点过赞
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    boolean hasLiked(Integer likedUserId, Long likedPostId);

    /**
     * 已经取消点赞
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    boolean hasUnLiked(Integer likedUserId, Long likedPostId);

    void saveLiked2Redis(Integer likedUserId, Long likedPostId);

    void unlikeFromRedis(Integer likedUserId, Long likedPostId);

    void deleteLikedFromRedis(Integer likedUserId, Long likedPostId);

    void incrLikedCount(Long likedPostId);

    void decrLikedCount(Long likedPostId);

    List<FmsUserLikePost> listLikedDataFromRedis();

    List<FmsLikedCountDTO> listLikedCountFromRedis();
}
