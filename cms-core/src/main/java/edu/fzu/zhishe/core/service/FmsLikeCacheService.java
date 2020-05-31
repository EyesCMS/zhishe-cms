package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.FmsUserLikePost;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.core.constant.LikedStatusEnum;
import edu.fzu.zhishe.core.dto.FmsLikedCountDTO;
import java.util.List;
import java.util.Set;

/**
 * 点赞缓存操作类
 *
 * @author liang
 * @date 2020/5/15
 */
public interface FmsLikeCacheService {

    /**
     * 获取更新过的点赞帖子 id
     * @return
     */
    Set<Object> listUpdatedPost();

    /**
     * 测试用户是否已经对帖子点过赞
     * @param userId 点赞用户 id
     * @param postId 待测试帖子 id
     * @return true if user has liked post
     */
    boolean hasLiked(Integer userId, Long postId);

    /**
     * 获取帖子点赞数
     * @param postId 帖子 id
     * @return 帖子的点赞数
     */
    Integer getLikeCount(Long postId);

    /**
     * 点赞
     * @param uid 用户 id
     * @param pid 帖子 id
     */
    void like(Integer uid, Long pid);

    /**
     * 取消点赞
     * @param uid 用户 id
     * @param pid 帖子 id
     */
    void unlike(Integer uid, Long pid);

    /**
     * 某个帖子的点赞数 +1
     * @param pid 帖子 id
     */
    void incrLikedCount(Long pid);

    /**
     * 某个帖子的点赞数 -1
     * @param pid 帖子 id
     */
    void decrLikedCount(Long pid);

    /**
     * 删除某个帖子的点赞信息
     * @param pid 帖子 id
     */
    void deletePostLikeSet(Long pid);

    /**
     * 获取帖子点赞数据列表
     */
    List<FmsUserLikePost> listLikedData();

    /**
     * 获取帖子点赞数列表
     */
    List<FmsLikedCountDTO> listLikedCount();

    /**
     * 同步帖子点赞数据到数据库
     */
    void syncLikeDataToDatabase();
}
