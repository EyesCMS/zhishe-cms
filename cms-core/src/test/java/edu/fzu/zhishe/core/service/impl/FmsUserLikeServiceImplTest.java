package edu.fzu.zhishe.core.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.model.FmsPost;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.constant.LikedStatusEnum;
import edu.fzu.zhishe.core.service.FmsForumService;
import edu.fzu.zhishe.core.service.FmsUserLikeService;
import edu.fzu.zhishe.core.util.MockUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/27/2020
 */
@SpringBootTest
class FmsUserLikeServiceImplTest {

    Logger log = LoggerFactory.getLogger(FmsForumServiceImplTest.class);

    @Autowired
    FmsUserLikeService userLikeService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userLikeService.like(54L);
        }, " 帖子不存在，却可以对其点赞 ");

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userLikeService.unlike(54L);
        }, " 帖子不存在，却可以对其取消点赞 ");

        Assertions.assertThrows(ApiException.class, () -> {
            Long postId = 57L;
            if (userLikeService.getLikeStatus(postId).equals(LikedStatusEnum.LIKE.getCode())) {
                userLikeService.like(postId);
            } else {
                userLikeService.like(postId);
                userLikeService.like(postId);
            }
        }, " 已经对某一帖子点过赞，却可以再次点赞 ");

        Assertions.assertThrows(ApiException.class, () -> {
            Long postId = 57L;
            if (userLikeService.getLikeStatus(postId).equals(LikedStatusEnum.UNLIKE.getCode())) {
                userLikeService.unlike(postId);
            } else {
                userLikeService.unlike(postId);
                userLikeService.unlike(postId);
            }
        }, " 还没对某一帖子点过赞，却可以取消点赞 ");
    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

        // user test 未点赞的帖子 id
        long postId = 57L;

        Assertions.assertDoesNotThrow(() -> {
            userLikeService.like(postId);
        }, "点赞异常");

        Assertions.assertDoesNotThrow(() -> {
            userLikeService.unlike(postId);
        }, "取消点赞异常");
    }
}
