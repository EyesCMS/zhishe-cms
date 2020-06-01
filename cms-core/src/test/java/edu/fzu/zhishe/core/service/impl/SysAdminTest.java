package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.AccessDeniedException;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.SysAdminNewUsersQuery;
import edu.fzu.zhishe.core.service.CmsActivityService;
import edu.fzu.zhishe.core.service.SysAdminService;
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
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
@SpringBootTest
public class SysAdminTest {
    Logger log = LoggerFactory.getLogger(SysAdminService.class);

    @Autowired
    SysAdminService adminService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation() {
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            adminService.clubSpecies();
            }, " 非管理员，却可以获取数据 ");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            adminService.newUsers(new SysAdminNewUsersQuery());
            }, " 非管理员，却可以获取数据 ");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            adminService.unaudited();
            }, " 非管理员，却可以获取数据 ");
    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {
        MockUtil.mockLoginUser("test9");

        Assertions.assertDoesNotThrow(() -> {
            adminService.clubSpecies();
            }, " 管理员获取数据异常 ");

        Assertions.assertDoesNotThrow(() -> {
            adminService.newUsers(new SysAdminNewUsersQuery());
        }, " 管理员获取数据异常 ");

        Assertions.assertDoesNotThrow(() -> {
            adminService.unaudited();
        }, " 管理员获取数据异常 ");
    }
}
