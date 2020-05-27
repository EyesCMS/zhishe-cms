package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.core.service.CreditService;
import edu.fzu.zhishe.core.util.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yang on 5/27/2020.
 * @version 1.0
 */
@SpringBootTest
public class CreditServiceImplTest {
    Logger log = LoggerFactory.getLogger(CreditServiceImplTest.class);
    @Autowired
    CreditService creditService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){

    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

    }
}
