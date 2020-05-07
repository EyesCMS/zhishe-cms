package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsQuitNotice;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.constant.ApplyStateEnum;
import edu.fzu.zhishe.core.param.*;
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

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){
        int clubIdNotExist = 9999;
        int applyIdNotExist = 9999;
        int clubIdRepeat = 10014;
        int applyIdHasAudited = 10001;

        String clubNameExisted = "7社";
        String clubNameRepeat = "单元测试";

        CmsClubsCreationsParam cmsClubsCreationsParam = new CmsClubsCreationsParam(){{
            setClubName(clubNameExisted);
            setOfficialState(1);
            setReason("xx");
            setType("xx");
        }};
        CmsClubsCreationsParam cmsClubsCreationsParamRepeat = new CmsClubsCreationsParam(){{
            setClubName(clubNameRepeat);
            setOfficialState(1);
            setReason("xx");
            setType("xx");
        }};

        CmsClubsAuditParam cmsClubsAuditParamApplyIdNotExist = new CmsClubsAuditParam(){{
            setId(applyIdNotExist);
            setState(ApplyStateEnum.PENDING.getValue());
        }};
        CmsClubsAuditParam cmsClubsAuditParamHasAudited = new CmsClubsAuditParam(){{
            setId(applyIdHasAudited);
            setState(ApplyStateEnum.PENDING.getValue());
        }};

        CmsClubsDisbandParam cmsClubsDisbandParamClubIdNotExist = new CmsClubsDisbandParam(){{
           setClubId(clubIdNotExist);
           setReason("xx");
        }};
//        CmsClubsDisbandParam cmsClubsDisbandParamClubIdRePeat = new CmsClubsDisbandParam(){{
//            setClubId(clubIdRepeat);
//            setReason("xx");
//        }};

        CmsClubsJoinParam cmsClubsJoinParamClubIdNotExist= new CmsClubsJoinParam(){{
            setClubId(clubIdNotExist);
            setReason("xx");
        }};

        CmsClubsQuitParam cmsClubsQuitParamClubIdNotExist = new CmsClubsQuitParam(){{
            setClubId(clubIdNotExist);
            setReason("xx");
        }};

        CmsClubsChiefChangeParam cmsClubsChiefChangeParamClubIdNotExist = new CmsClubsChiefChangeParam(){{
            setClubId(clubIdNotExist);
            setNewChiefName("xx");
            setReason("xx");
        }};

        CmsClubsCertificationsParam cmsClubsCertificationsParamClubIdNotExist = new CmsClubsCertificationsParam(){{
            setClubId(clubIdNotExist);
            setReason("xx");
        }};

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

//        Assertions.assertThrows(ApiException.class, () -> {
//            cmsApplyAuditService.clubDisband(cmsClubsDisbandParamClubIdRePeat);
//        }, " 社团解散申请已存在并且还没审核，却可以重复申请 ");

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

    }
}