package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import edu.fzu.zhishe.common.exception.AccessDeniedException;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.core.service.CreditService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yang on 5/27/2020.
 * @version 1.0
 */
@SpringBootTest
public class CreditServiceImplTest {
    Logger log = LoggerFactory.getLogger(CreditServiceImplTest.class);
    @Autowired
    CreditService creditService;

    @Autowired
    CmsUserClubRelMapper cmsUserClubRelMapper;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("test");
    }

    @Test
    @Transactional
    @Rollback
    public void TestForbiddenOperation(){
        int userId = 10088;
        int clubId = 10000;
        int clubIdNotExist = 1;
        Date todayDate = new Date();

        //签到
        Assertions.assertThrows(ApiException.class, () -> {
            creditService.checkin(clubIdNotExist,todayDate);
        }, " 成员记录不存在，却可以签到 ");

        //伪造一个签到日期是今天的rel记录
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId);
        List<CmsUserClubRel> cmsUserClubRelList = cmsUserClubRelMapper.selectByExample(example);
        CmsUserClubRel newUserClubRel = cmsUserClubRelList.get(0);
        newUserClubRel.setCheckInDate(todayDate);
        cmsUserClubRelMapper.updateByPrimaryKeySelective(newUserClubRel);

        Assertions.assertThrows(AccessDeniedException.class, () -> {
            creditService.checkin(clubId,todayDate);
        }, " 今日已签到，却可以签到 ");

        //获取个人活跃度信息
        Assertions.assertThrows(ApiException.class, () -> {
            creditService.getUserHonor(clubIdNotExist);
        }, " 成员记录不存在，却可以获取活跃度 ");

        //获取社团等级信息
        Assertions.assertThrows(ApiException.class, () -> {
            creditService.getClubGrade(clubIdNotExist);
        }, " 社团不存在，却可以获取等级 ");


    }

    @Test
    @Transactional
    @Rollback
    public void TestAcceptedOperation() throws ParseException {
        int userId = 10088;
        int clubId = 10000;
        Date todayDate = new Date();
        String string = "2016-10-24 21:59:06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date anotherDate = sdf.parse(string);

        //签到
        //伪造未签到rel记录
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andClubIdEqualTo(clubId).andUserIdEqualTo(userId);
        List<CmsUserClubRel> cmsUserClubRelList = cmsUserClubRelMapper.selectByExample(example);
        CmsUserClubRel newUserClubRel = cmsUserClubRelList.get(0);
        newUserClubRel.setCheckInDate(anotherDate);
        cmsUserClubRelMapper.updateByPrimaryKeySelective(newUserClubRel);

        Assertions.assertDoesNotThrow(() -> {
            creditService.checkin(clubId,todayDate);
        }, " 存在成员记录且今天已签到，却不可以签到 ");

        //获取个人活跃度信息
        Assertions.assertDoesNotThrow(() -> {
            creditService.getUserHonor(clubId);
        }, " 成员记录存在，却不可以获取活跃度 ");

        //获取社团等级信息
        Assertions.assertDoesNotThrow(() -> {
            creditService.getClubGrade(clubId);
        }, " 社团存在，却不可以获取等级 ");

    }
}
