package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import java.util.List;

/**
 * @author liang on 5/15/2020.
 * @version 1.0
 */
public interface FmsUserLikeService {

    /**
     * 保存点赞记录
     * @param record
     * @return
     */
    int save(FmsUserLikePost record);

    /**
     * 批量保存或修改
     * @param records
     * @return
     */
    int saveAll(List<FmsUserLikePost> records);

    /**
     * 根据被点赞帖子查询点赞列表
     * @param postId 被点赞帖子 id
     * @return
     */
    List<FmsUserLikePost> listLikedListByPostId(Long postId);

    /**
     * 根据点赞人查询点赞列表
     * @param userId 点赞人 id
     * @return
     */
    List<FmsUserLikePost> listLikedListByUserId(Integer userId);

    /**
     * 通过被点赞帖子和点赞人id 查询点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    FmsUserLikePost getByLikedUserIdAndLikedPostId(Integer likedUserId, Long likedPostId);

    /**
     * 将 Redis 中的点赞数据存入数据库
     */
    void transLikedFromRedis2DB();

    /**
     * 将 Redis 中的点赞数量存入数据库
     */
    void transLikedCountFromRedis2DB();
}
