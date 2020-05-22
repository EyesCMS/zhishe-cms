package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.CmsClubCreateApplyMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsQuitNotice;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.constant.ApplyStateEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.param.*;
import edu.fzu.zhishe.core.service.CmsActivityService;
import edu.fzu.zhishe.core.service.CmsApplyAuditService;
import edu.fzu.zhishe.core.service.SysUserService;
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

import java.util.Date;

/**
 * @author yang on 5/7/2020.
 * @version 1.0
 */
@SpringBootTest
public class CmsApplyAuditServiceImplTest{

    Logger log = LoggerFactory.getLogger(CmsApplyAuditServiceImplTest.class);
    @Autowired
    CmsApplyAuditService cmsApplyAuditService;

//    @Autowired
//    CmsClubMapper cmsClubMapper;
//
//    @Autowired
//    CmsClubCreateApplyMapper clubCreateApplyMapper;



    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test6");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){
        int clubIdNotExist = 9999;
        int applyIdNotExist = 9999;
        int clubIdMine = 10013;
        int clubIdRepeat = 10014;
        int clubIdNotMine = 10020;
        int applyIdHasAudited = 10001;

        String clubNameExisted = "7社";
        String clubNameRepeat = "单元测试";

        CmsClubsCreationsParam cmsClubsCreationsParam = new CmsClubsCreationsParam();
        cmsClubsCreationsParam.setClubName(clubNameExisted);
        cmsClubsCreationsParam.setOfficialState(1);
        cmsClubsCreationsParam.setReason("xx");
        cmsClubsCreationsParam.setType("xx");
        CmsClubsCreationsParam cmsClubsCreationsParamRepeat = new CmsClubsCreationsParam();
        cmsClubsCreationsParamRepeat.setClubName(clubNameRepeat);
        cmsClubsCreationsParamRepeat.setOfficialState(1);
        cmsClubsCreationsParamRepeat.setReason("xx");
        cmsClubsCreationsParamRepeat.setType("xx");

        CmsClubsAuditParam cmsClubsAuditParamApplyIdNotExist = new CmsClubsAuditParam();
        cmsClubsAuditParamApplyIdNotExist.setId(applyIdNotExist);
        cmsClubsAuditParamApplyIdNotExist.setState(ApplyStateEnum.PENDING.getValue());

        CmsClubsAuditParam cmsClubsAuditParamHasAudited = new CmsClubsAuditParam();
        cmsClubsAuditParamHasAudited.setId(applyIdHasAudited);
        cmsClubsAuditParamHasAudited.setState(ApplyStateEnum.PENDING.getValue());

        CmsClubsDisbandParam cmsClubsDisbandParamClubIdNotExist = new CmsClubsDisbandParam();
        cmsClubsDisbandParamClubIdNotExist.setClubId(clubIdNotExist);
        cmsClubsDisbandParamClubIdNotExist.setReason("xx");

        CmsClubsDisbandParam cmsClubsDisbandParamClubIdRePeat = new CmsClubsDisbandParam();
        cmsClubsDisbandParamClubIdRePeat.setClubId(clubIdRepeat);
        cmsClubsDisbandParamClubIdRePeat.setReason("xx");

        CmsClubsJoinParam cmsClubsJoinParamClubIdNotExist= new CmsClubsJoinParam();
        cmsClubsJoinParamClubIdNotExist.setClubId(clubIdNotExist);
        cmsClubsJoinParamClubIdNotExist.setReason("xx");

        CmsClubsJoinParam cmsClubsJoinParamClubHasJoined= new CmsClubsJoinParam();
        cmsClubsJoinParamClubHasJoined.setClubId(clubIdMine);
        cmsClubsJoinParamClubHasJoined.setReason("xx");

        CmsClubsQuitParam cmsClubsQuitParamClubIdNotExist = new CmsClubsQuitParam();
        cmsClubsQuitParamClubIdNotExist.setClubId(clubIdNotExist);
        cmsClubsQuitParamClubIdNotExist.setReason("xx");

        CmsClubsQuitParam cmsClubsQuitParamClubIdNotMember = new CmsClubsQuitParam();
        cmsClubsQuitParamClubIdNotMember.setClubId(clubIdNotMine);
        cmsClubsQuitParamClubIdNotMember.setReason("xx");

        CmsClubsChiefChangeParam cmsClubsChiefChangeParamClubIdNotExist = new CmsClubsChiefChangeParam();
        cmsClubsChiefChangeParamClubIdNotExist.setClubId(clubIdNotExist);
        cmsClubsChiefChangeParamClubIdNotExist.setNewChiefName("xx");
        cmsClubsChiefChangeParamClubIdNotExist.setReason("xx");

        CmsClubsCertificationsParam cmsClubsCertificationsParamClubIdNotExist = new CmsClubsCertificationsParam();
        cmsClubsCertificationsParamClubIdNotExist.setClubId(clubIdNotExist);
        cmsClubsCertificationsParamClubIdNotExist.setReason("xx");

        //提交创建社团申请
        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.createClub(cmsClubsCreationsParam);
        }, " 社团已存在，却可以创建 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.createClub(cmsClubsCreationsParamRepeat);
        }, " 社团创建申请已存在并且还没审核，却可以重复申请 ");

        //审核社团申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubCreationsAudit(cmsClubsAuditParamApplyIdNotExist);
        }, " 申请不存在，却可以审核 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.clubCreationsAudit(cmsClubsAuditParamHasAudited);
        }, " 申请已审核完毕，却可以审核 ");

        //提交社团解散申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubDisband(cmsClubsDisbandParamClubIdNotExist);
        }, " 社团不存在，却可以解散 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.clubDisband(cmsClubsDisbandParamClubIdRePeat);
        }, " 社团解散申请已存在并且还没审核，却可以重复申请 ");

        //审核社团解散申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubDissolutionAudit(cmsClubsAuditParamApplyIdNotExist);
        }, " 申请不存在，却可以审核 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.clubDissolutionAudit(cmsClubsAuditParamHasAudited);
        }, " 申请已审核完毕，却可以审核 ");

        //提交社团加入申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubJoin(cmsClubsJoinParamClubIdNotExist);
        }, " 社团不存在，却可以加入 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.clubJoin(cmsClubsJoinParamClubHasJoined);
        }, " 已是社团成员，却可以加入 ");

        //查看社团加入申请列表
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.listJoinClubApply(clubIdNotExist,new CmsClubsJoinQuery(),new PaginationParam());
        }, " 社团不存在，却可以查看加入列表 ");

        //审核社团加入申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubJoinsAudit(cmsClubsAuditParamApplyIdNotExist);
        }, " 申请不存在，却可以审核 ");

        //提交社团退出信息
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubQuit(cmsClubsQuitParamClubIdNotExist);
        }, " 社团不存在，却可以退出 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.clubQuit(cmsClubsQuitParamClubIdNotMember);
        }, " 非社团成员，却可以退出 ");

        //查看社团退出信息列表
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.listClubQuit(clubIdNotExist,new CmsClubsQuitQuery(),new PaginationParam());
        }, " 社团不存在，却可以查看退出信息列表 ");

        //提出社团换届申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubChiefChange(cmsClubsChiefChangeParamClubIdNotExist);
        }, " 社团不存在，却可以换届 ");

        //审核社团换届申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubChiefChangeAudit(cmsClubsAuditParamApplyIdNotExist);
        }, " 申请不存在，却可以审核 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.clubChiefChangeAudit(cmsClubsAuditParamHasAudited);
        }, " 申请已审核完毕，却可以审核 ");

        //提出社团认证申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubOfficialChange(cmsClubsCertificationsParamClubIdNotExist);
        }, " 社团不存在，却可以认证 ");

        //审核社团换届申请
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            cmsApplyAuditService.clubOfficialChangeAudit(cmsClubsAuditParamApplyIdNotExist);
        }, " 申请不存在，却可以审核 ");

        Assertions.assertThrows(ApiException.class, () -> {
            cmsApplyAuditService.clubOfficialChangeAudit(cmsClubsAuditParamHasAudited);
        }, " 申请已审核完毕，却可以审核 ");
    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() {
        int clubIdExisted = 10013;
        int clubIdMine = 10014;
        int clubIdHasNotJoined = 5000;
        int clubIdHasJoin = 10000;
        int applyIdHasNotAudited = 10000;

        String clubNameNotExisted = "还不存在社";


        CmsClubsCreationsParam cmsClubsCreationsParam = new CmsClubsCreationsParam();
        cmsClubsCreationsParam.setClubName(clubNameNotExisted);
        cmsClubsCreationsParam.setOfficialState(1);
        cmsClubsCreationsParam.setReason("xx");
        cmsClubsCreationsParam.setType("xx");

        CmsClubsAuditParam cmsClubsAuditParamHasNotAudited = new CmsClubsAuditParam();
        cmsClubsAuditParamHasNotAudited.setId(applyIdHasNotAudited);
        cmsClubsAuditParamHasNotAudited.setState(ApplyStateEnum.PENDING.getValue());

        CmsClubsDisbandParam cmsClubsDisbandParamClubIdExisted = new CmsClubsDisbandParam();
        cmsClubsDisbandParamClubIdExisted.setClubId(clubIdExisted);
        cmsClubsDisbandParamClubIdExisted.setReason("xx");

        CmsClubsJoinParam cmsClubsJoinParamClubIdExisted= new CmsClubsJoinParam();
        cmsClubsJoinParamClubIdExisted.setClubId(clubIdHasNotJoined);
        cmsClubsJoinParamClubIdExisted.setReason("xx");

        CmsClubsQuitParam cmsClubsQuitParamClubIdExisted = new CmsClubsQuitParam();
        cmsClubsQuitParamClubIdExisted.setClubId(clubIdHasJoin);
        cmsClubsQuitParamClubIdExisted.setReason("xx");

        CmsClubsChiefChangeParam cmsClubsChiefChangeParamClubIdExisted = new CmsClubsChiefChangeParam();
        cmsClubsChiefChangeParamClubIdExisted.setClubId(clubIdMine);
        cmsClubsChiefChangeParamClubIdExisted.setNewChiefName("test");
        cmsClubsChiefChangeParamClubIdExisted.setReason("xx");

        CmsClubsCertificationsParam cmsClubsCertificationsParamClubIdExisted = new CmsClubsCertificationsParam();
        cmsClubsCertificationsParamClubIdExisted.setClubId(clubIdMine);
        cmsClubsCertificationsParamClubIdExisted.setReason("xx");

        //提交创建社团申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.createClub(cmsClubsCreationsParam);
        }, " 社团不存在，却不可以创建 ");

        //审核社团申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubCreationsAudit(cmsClubsAuditParamHasNotAudited);
        }, " 申请存在且未审核，却不可以审核 ");

        //提交社团解散申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubDisband(cmsClubsDisbandParamClubIdExisted);
        }, " 社团存在，却不可以解散 ");

        //审核社团解散申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubDissolutionAudit(cmsClubsAuditParamHasNotAudited);
        }, " 申请存在且还未审核，却不可以审核 ");

        //提交社团加入申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubJoin(cmsClubsJoinParamClubIdExisted);
        }, " 不是社团成员，却不可以加入 ");

        //查看社团加入申请列表
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.listJoinClubApply(clubIdExisted,new CmsClubsJoinQuery(),new PaginationParam());
        }, " 社团存在且为社长，却不可以查看加入列表 ");
//
        //审核社团加入申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubJoinsAudit(cmsClubsAuditParamHasNotAudited);
        }, " 申请存在且还未审核，却不可以审核 ");

        //提交社团退出信息
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubQuit(cmsClubsQuitParamClubIdExisted);
        }, " 是社团成员，却不可以退出 ");

        //查看社团退出信息列表
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.listClubQuit(clubIdExisted,new CmsClubsQuitQuery(),new PaginationParam());
        }, " 社团存在且为社长，却不可以查看退出信息列表 ");

        //提出社团换届申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubChiefChange(cmsClubsChiefChangeParamClubIdExisted);
        }, " 社团存在且为社长，新社长为存在的社团成员，却不可以换届 ");

        //审核社团换届申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubChiefChangeAudit(cmsClubsAuditParamHasNotAudited);
        }, " 申请存在且还未审核，却不可以审核 ");

        //提出社团认证申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubOfficialChange(cmsClubsCertificationsParamClubIdExisted);
        }, " 社团存在且为社长，社团为小团体类型，却不可以认证 ");

        //审核社团换届申请
        Assertions.assertDoesNotThrow(() -> {
            cmsApplyAuditService.clubOfficialChangeAudit(cmsClubsAuditParamHasNotAudited);
        }, " 申请存在且还未审核，却不可以审核 ");

    }
}
