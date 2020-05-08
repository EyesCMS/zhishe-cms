package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.AccessDeniedException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.param.CmsClubInfoParam;
import edu.fzu.zhishe.core.param.CmsClubMemberQuery;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsClubService;
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

    Logger log = LoggerFactory.getLogger(CmsClubServiceImplTest.class);
    @Autowired
    CmsClubService cmsClubService;
    PaginationParam paginationParam;
    CmsClubMemberQuery clubMemberQuery;
    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){

        //修改社团信息
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            CmsClubInfoParam param = new CmsClubInfoParam() {{
                setQqGroup("");
                setSlogan("");
                setType("");
            }};
            cmsClubService.updateClubInfo(id2, param);
        }, " 社团类型不能为空");

        //修改社团头像
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.alterClubAvatarUrl(id1,
                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }, " 没有该权限");
    }
    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {

    }
}
