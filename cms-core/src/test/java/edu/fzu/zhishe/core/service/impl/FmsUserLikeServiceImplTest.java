package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.service.FmsLikeCacheService;
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
    FmsLikeCacheService likeCacheService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("xjliang");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation() {
        Integer uid = 7;
        Long postIdNotExist = 54L;
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            likeCacheService.like(uid, postIdNotExist);
        }, " 帖子不存在，却可以对其点赞 ");

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            likeCacheService.unlike(uid, postIdNotExist);
        }, " 帖子不存在，却可以对其取消点赞 ");

        Assertions.assertThrows(ApiException.class, () -> {
            Long postId = 4L;
            if (likeCacheService.hasLiked(uid, postId)) {
                likeCacheService.like(uid, postId);
            } else {
                likeCacheService.like(uid, postId);
                likeCacheService.like(uid, postId);
            }
        }, " 已经对某一帖子点过赞，却可以再次点赞 ");

        Assertions.assertThrows(ApiException.class, () -> {
            Long postId = 4L;
            if (!likeCacheService.hasLiked(uid, postId)) {
                likeCacheService.unlike(uid, postId);
            } else {
                likeCacheService.unlike(uid, postId);
                likeCacheService.unlike(uid, postId);
            }
        }, " 还没对某一帖子点过赞，却可以取消点赞 ");
    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

        Integer uid = 7;
        // user test 未点赞的帖子 id
        long postId = 4L;

        Assertions.assertDoesNotThrow(() -> {
            likeCacheService.like(uid, postId);
        }, "点赞异常");

        Assertions.assertDoesNotThrow(() -> {
            likeCacheService.unlike(uid, postId);
        }, "取消点赞异常");
    }
}
