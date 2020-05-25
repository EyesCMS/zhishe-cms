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
    Integer joinedClubId = 10014;      //测试类登录的用户加入的社团ID
    Integer managedClubId = 10015;     //测试类登录的用户创建的社团ID
    Integer notJoinedClubId = 10020;   //测试类登录的用户未加入的社团ID
    Integer notExistedClubId = 101;    //不存在的社团ID

    Integer managerId = 10088;      //测试类用户登录的用户ID
    Integer memberId = 10089;       //加入 ID = joinedClubId 的用户
    Integer notMemberId = 10100;     //未加入任何社团的用户ID

    String [] urls = {null,null,null,null,null};  //社团走马灯图片url

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
        //3.7查看成员列表
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            PaginationParam paginationParam = new PaginationParam();
            CmsClubMemberQuery clubMemberQuery = new CmsClubMemberQuery();
            cmsClubService.listClubMember(paginationParam, notJoinedClubId, clubMemberQuery);
        }, " 非社员，没有该权限，无法查看成员列表。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            PaginationParam paginationParam = new PaginationParam();
            CmsClubMemberQuery clubMemberQuery = new CmsClubMemberQuery();
            cmsClubService.listClubMember(paginationParam, notExistedClubId, clubMemberQuery);
        }, " 该社团不存在，无法查看成员列表。");

        //3.8查看成员详情
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.getClubMemberInfo(notJoinedClubId, managerId);
        }, " 没有该权限，无法查看成员详情。");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            cmsClubService.getClubMemberInfo(joinedClubId, notMemberId);
        }, " 用户不存在，无法查看成员详情。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.getClubMemberInfo(notExistedClubId, managerId);
        }, " 该社团不存在，无法查看成员详情。");

        //3.10删除成员
        Assertions.assertThrows(ApiException.class, () -> {
            cmsClubService.deleteClubMember(managedClubId,
                    notMemberId);
        }, " 不存在该成员。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.deleteClubMember(notJoinedClubId,
                    managerId);
        }, " 非社员，没有该权限。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.deleteClubMember(joinedClubId,
                    memberId);
        }, " 非社长，没有该权限。");

        //3.11修改社团信息
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            CmsClubInfoParam param = new CmsClubInfoParam();
            param.setQqGroup("");
            param.setSlogan("");
            param.setType("运动");
            cmsClubService.updateClubInfo(joinedClubId, param);
        }, " 非社长，无法修改社团信息。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            CmsClubInfoParam param = new CmsClubInfoParam();
            param.setQqGroup("");
            param.setSlogan("");
            param.setType("运动");
            cmsClubService.updateClubInfo(notJoinedClubId, param);
        }, " 非社团成员，没有权限。");

        Assertions.assertThrows(ApiException.class, () -> {
            CmsClubInfoParam param = new CmsClubInfoParam();
            param.setQqGroup("");
            param.setSlogan("");
            param.setType("");
            cmsClubService.updateClubInfo(managedClubId, param);
        }, " 社团类型不能为空。");

        //3.12修改社团头像
        Assertions.assertThrows(ApiException.class, () -> {
            cmsClubService.updateClubAvatarUrl(managedClubId,
                    "");
        }, " 头像不能为空。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.updateClubAvatarUrl(joinedClubId,
                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }, " 非社长，没有该权限。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.updateClubAvatarUrl(notJoinedClubId,
                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }, " 非社员，没有该权限。");

        //3.15修改社团走马灯
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.updateClubPictureUrl(joinedClubId, urls);
        }, " 非社长，没有该权限。");

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            cmsClubService.updateClubPictureUrl(notJoinedClubId, urls);
        }, " 非社员，没有该权限。");

    }
    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {
        //3.7查看成员列表
        Assertions.assertDoesNotThrow(() -> {
            PaginationParam paginationParam = new PaginationParam();
            CmsClubMemberQuery clubMemberQuery = new CmsClubMemberQuery();
            cmsClubService.listClubMember(paginationParam, joinedClubId, clubMemberQuery);
        }, " 没有该权限");

        //3.8查看成员详情
        Assertions.assertDoesNotThrow(() -> {
            cmsClubService.getClubMemberInfo(joinedClubId, managerId);
        }, " 没有该权限");

        //3.10删除社员
        Assertions.assertDoesNotThrow(() -> {
            cmsClubService.deleteClubMember(managedClubId,
                    memberId);
        }, " 没有该权限");

        //3.11修改社团信息
        Assertions.assertDoesNotThrow(() -> {
            CmsClubInfoParam param = new CmsClubInfoParam();
            param.setQqGroup("");
            param.setSlogan("");
            param.setType("运动");
            cmsClubService.updateClubInfo(managedClubId, param);
        }, " 社团类型不能为空");

        //3.12修改社团头像
        Assertions.assertDoesNotThrow(() -> {
            cmsClubService.updateClubAvatarUrl(managedClubId,
                    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }, " 没有该权限 ");

        //3.15修改社员走马灯
        Assertions.assertDoesNotThrow(() -> {
            cmsClubService.updateClubPictureUrl(managedClubId, urls);
        }, " 没有该权限 ");
    }
}
