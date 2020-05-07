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
 * @author yang on 5/7/2020.
 * @version 1.0
 */
@SpringBootTest
public class CmsApplyAuditServiceImplTest{

    Logger log = LoggerFactory.getLogger(CmsApplyAuditServiceImplTest.class);
    @Autowired
    CmsApplyAuditService cmsApplyAuditService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){
        int clubIdNotExist = 9999;
        String clubNameExisted = "7社";
        CmsClubsCreationsParam clubsCreationsParam = new CmsClubsCreationsParam(){{
            setClubName(clubNameExisted);
            setOfficialState(1);
            setReason("xx");
            setType("xx");
        }};

        //提交创建社团申请
        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.createClub(clubsCreationsParam);
        }, " 社团已存在，却可以创建 ");
    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

    }
}