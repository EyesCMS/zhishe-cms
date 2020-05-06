package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.AccessDeniedException;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.param.FmsPostParam;
import edu.fzu.zhishe.core.param.FmsRemarkParam;
import edu.fzu.zhishe.core.service.FmsForumService;
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
 * @author liang on 5/6/2020.
 * @version 1.0
 */
@SpringBootTest
public class FmsForumServiceImplTest {

    Logger log = LoggerFactory.getLogger(FmsForumServiceImplTest.class);
    @Autowired
    FmsForumService forumService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation() {

        // 不存在的帖子id
        Long postIdNotExist = 999999999L;
        // 已删除的个人帖子 id
        Long deletedPostId = 10L;
        // 社团帖子id
        Long activityPostId = 2L;

        // 更新帖子
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            forumService.updatePost(postIdNotExist, new FmsPostParam());
        }, " 帖子不存在，却可以更新 ");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            forumService.updatePost(deletedPostId, new FmsPostParam());
        }, " 帖子不存在，却可以更新 ");

        Assertions.assertThrows(ApiException.class, () -> {
            forumService.updatePost(activityPostId, new FmsPostParam());
        }, " 帖子类型是活动帖，却可以更新 ");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            forumService.updatePost(9L, new FmsPostParam());
        }, " 不是发帖人，却可以更新帖子 ");

        // 删除帖子
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            forumService.deletePost(postIdNotExist);
        }, " 帖子不存在，却可以删除 ");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            forumService.deletePost(deletedPostId);
        }, " 帖子不存在，却可以删除 ");

        Assertions.assertThrows(ApiException.class, () -> {
            forumService.deletePost(activityPostId);
        }, " 帖子类型是活动帖，却可以删除 ");

        // 发表评论
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            FmsRemarkParam remarkParam = new FmsRemarkParam() {{
                setPostId(postIdNotExist);
            }};
            forumService.saveRemark(remarkParam);
        }, " 帖子不存在，却可以对其发表评论 ");

        // 删除评论
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            forumService.deletePost(9L);
        }, " 不是评论发布者，却可以删除评论 ");
    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

    }
}
