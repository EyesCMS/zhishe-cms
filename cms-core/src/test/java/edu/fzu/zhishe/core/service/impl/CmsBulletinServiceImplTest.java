package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.AccessDeniedException;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.param.CmsBulletinParam;
import edu.fzu.zhishe.core.service.CmsBulletinService;
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
 * @author zou
 */
@SpringBootTest
public class CmsBulletinServiceImplTest{
    Logger log = LoggerFactory.getLogger(CmsBulletinServiceImplTest.class);

    @Autowired
    CmsBulletinService bulletinService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){

        //不存在的公告id
        int bulletinIdNotExist = 99999;
        //已被删除的公告id
        int deletedBulletinId = 88888;
        //社团id
        int clubId = 10000;

        //发布公告
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bulletinService.creatBulletin(clubId, new CmsBulletinParam());
        }, " 不是社长，却可以发布 ");

        // 更新公告
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bulletinService.updateBulletin(bulletinIdNotExist, new CmsBulletinParam());
        }, " 公告不存在，却可以更新 ");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bulletinService.updateBulletin(deletedBulletinId, new CmsBulletinParam());
        }, " 公告不存在，却可以更新 ");

        //删除公告
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bulletinService.deleteBulletin(bulletinIdNotExist);
        }, " 公告不存在，却可以删除 ");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bulletinService.deleteBulletin(deletedBulletinId);
        }, " 公告不存在，却可以删除 ");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bulletinService.deleteBulletin(1);
        }, " 不是社长，却可以删除 ");

    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

        //存在的公告id，且是 test 发的
        int bulletinId = 51;

        CmsBulletinParam bulletinParam = new CmsBulletinParam () {{
            setTitle("new title");
            setBody("new content");
        }};

        //更新公告
        Assertions.assertDoesNotThrow(() -> {
            bulletinService.updateBulletin(bulletinId,bulletinParam);
        }, " 公告更新异常 ");

        //删除公告
        Assertions.assertDoesNotThrow(() -> {
            bulletinService.deleteBulletin(bulletinId);
        }, " 公告删除异常 ");
    }

}