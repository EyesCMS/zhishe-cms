package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.AccessDeniedException;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.param.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.FmsPostParam;
import edu.fzu.zhishe.core.param.FmsRemarkParam;
import edu.fzu.zhishe.core.service.CmsActivityService;
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
 * @author PSF(52260506 @ qq.com)
 * @
 */
@SpringBootTest
public class CmsActivityServiceImplTest {
    Logger log = LoggerFactory.getLogger(CmsActivityService.class);
    @Autowired
    CmsActivityService activityService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation() {

        // 不存在的活动id
        Integer activityIdNotExist = 999999;
        // 已删除的活动 id
        Integer deletedActivityId = 1;
        // 社团帖子id
        Integer activityId = 2;

        // 创建活动申请
        Assertions.assertThrows(NullPointerException.class, () -> {
            activityService.activityApply(new CmsClubActivityParam());
        }, " 非社长，却可以创建活动 ");


        Assertions.assertThrows(AccessDeniedException.class, () -> {
            activityService.activityStateChange(deletedActivityId, 0, UserRoleEnum.ADMIN);
        }, " 非管理员，却可以修改活动申请状态 ");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            activityService.activityStateChange(deletedActivityId, 0, UserRoleEnum.CHIEF);
        }, " 非社长，却可以修改活动申请状态 ");


        Assertions.assertThrows(NullPointerException.class, () -> {
            activityService.delActivity(activityIdNotExist);
        }, " 不存在的活动，却可以删除 ");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            activityService.delActivity(activityId);
        }, " 非社长或管理员，却可以删除活动 ");

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            activityService.updateActivity(deletedActivityId, new CmsActivityUpdateParam());
        }, " 已删除的活动，却可以修改 ");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            activityService.updateActivity(activityId, new CmsActivityUpdateParam());
        }, " 非管理员，却可以修改 ");

    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {
        // 存在的活动id
        Integer activityIdNotExist = 999999;
        // 社团帖子id
        Integer activityId = 2;


//        // 更新活动
//        Assertions.assertDoesNotThrow(() -> {
//            forumService.updatePost(personalPostId, postParam);
//        }, " 帖子更新异常 ");
//
//        // 删除活动
//        Assertions.assertDoesNotThrow(() -> {
//            forumService.deletePost(personalPostId);
//        }, " 帖子删除异常 ");
//
//
//        // 发表评论
//        Assertions.assertDoesNotThrow(() -> {
//            FmsRemarkParam remarkParam = new FmsRemarkParam();
//            remarkParam.setPostId(personalPostId2);
//            forumService.saveRemark(remarkParam);
//        }, " 发表评论异常 ");
//
//        // 删除评论
//        Assertions.assertDoesNotThrow(() -> {
//            forumService.deleteRemark(remarkId);
//        }, " 删除评论异常 ");
    }
}
