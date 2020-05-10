package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.common.exception.AccessDeniedException;
import edu.fzu.zhishe.common.exception.ApiException;
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
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wjh674 on 5/7/2020.
 * @version 1.0
 */
@SpringBootTest
public class CmsClubServiceImplTest{
    Integer joinedClubId = 10014;
    Integer managedClubId = 10015;
    Integer notJoinedClubId = 10020;

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
        //查看成员列表
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            PaginationParam paginationParam = new PaginationParam();
            CmsClubMemberQuery clubMemberQuery = new CmsClubMemberQuery();
            cmsClubService.listClubMember(paginationParam, notJoinedClubId, clubMemberQuery);
        }, " 没有该权限");

        //查看成员详情
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.showClubMemberInfo(notJoinedClubId, 10088);
        }, " 没有该权限");

        //修改社团信息
        Assertions.assertThrows(ApiException.class, () -> {
            CmsClubInfoParam param = new CmsClubInfoParam() {{
                setQqGroup("");
                setSlogan("");
                setType("运动");
            }};
            cmsClubService.updateClubInfo(joinedClubId, param);
        }, " 没有该权限");
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            CmsClubInfoParam param = new CmsClubInfoParam() {{
                setQqGroup("");
                setSlogan("");
                setType("运动");
            }};
            cmsClubService.updateClubInfo(notJoinedClubId, param);
        }, " 没有该权限");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            CmsClubInfoParam param = new CmsClubInfoParam() {{
                setQqGroup("");
                setSlogan("");
                setType("");
            }};
            cmsClubService.updateClubInfo(managedClubId, param);
        }, " 社团类型不能为空");

        //修改社团头像
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsClubService.alterClubAvatarUrl(managedClubId,
                    "");
        }, " 头像不能为空");
        Assertions.assertThrows(ApiException.class, () -> {
            cmsClubService.alterClubAvatarUrl(joinedClubId,
                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }, " 没有该权限");
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.alterClubAvatarUrl(notJoinedClubId,
                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }, " 没有该权限");
    }
    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {
        //查看成员列表
        Assertions.assertDoesNotThrow(() -> {
            PaginationParam paginationParam = new PaginationParam();
            CmsClubMemberQuery clubMemberQuery = new CmsClubMemberQuery();
            cmsClubService.listClubMember(paginationParam, joinedClubId, clubMemberQuery);
        }, " 没有该权限");

        //查看成员详情
        Assertions.assertDoesNotThrow(() -> {
            cmsClubService.showClubMemberInfo(joinedClubId, 10088);
        }, " 没有该权限");

        //修改社团信息
        Assertions.assertDoesNotThrow(() -> {
            CmsClubInfoParam param = new CmsClubInfoParam() {{
                setQqGroup("");
                setSlogan("");
                setType("运动");
            }};
            cmsClubService.updateClubInfo(managedClubId, param);
        }, " 社团类型不能为空");


        //修改社团头像
        Assertions.assertDoesNotThrow(() -> {
            cmsClubService.alterClubAvatarUrl(managedClubId,
                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }, " 没有该权限 ");
    }
}
