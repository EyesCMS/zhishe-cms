package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.param.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.param.FmsPostParam;
import edu.fzu.zhishe.core.service.CmsActivityService;
import edu.fzu.zhishe.core.service.CmsApplyAuditService;
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
 * @author wjh674 on 5/7/2020.
 * @version 1.0
 */
@SpringBootTest
public class CmsClubServiceImplTest{
    Integer id1 = 10020;
    Integer id2 = 10000;
    JSONObject object;

    Logger log = LoggerFactory.getLogger(CmsClubServiceImplTest.class);
    @Autowired
    CmsClubService cmsClubService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){
        //修改社团信息
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.alterClubAvatarUrl(id2,object);
        }, " 社团类型不能为空");


        //修改社团头像
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.alterClubAvatarUrl(id1,object);
        }, " url不能为空");
    }
    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

    }
}