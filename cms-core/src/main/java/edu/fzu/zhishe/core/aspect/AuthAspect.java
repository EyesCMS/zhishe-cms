package edu.fzu.zhishe.core.aspect;

import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.CheckClubAuth;
import edu.fzu.zhishe.core.annotation.IsClubMember;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author liang on 5/3/2020.
 * @version 1.0
 */
@Order(1)
@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Autowired
    SysUserService userService;

    @Autowired
    CmsClubMapper clubMapper;

    @Autowired
    CmsUserClubRelMapper userClubRelMapper;

    @Pointcut("@annotation(edu.fzu.zhishe.core.annotation.IsLogin)")
    public void checkLogin() { }


    @Pointcut("@annotation(edu.fzu.zhishe.core.annotation.CheckClubAuth)")
    public void checkAuth() { }

    @Before("checkLogin()")
    public void checkLogin(JoinPoint joinPoint) {
        SysUser currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }
    }

    /**
     * WARN: method annotated with this MUST has parameter named 'clubId'
     */
    @Around("checkAuth()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        SysUser currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        for (int i = 0; i < codeSignature.getParameterNames().length; i++) {
            if (!"CLUBID".equals(codeSignature.getParameterNames()[i].toUpperCase())) {
                continue;
            }

            Integer clubId = (Integer) joinPoint.getArgs()[i];
            CmsClub club = clubMapper.selectByPrimaryKey(clubId);
            if (club == null) {
                Asserts.notFound("社团不存在");
            }

            CmsUserClubRelExample example = new CmsUserClubRelExample();
            example.createCriteria()
                .andUserIdEqualTo(currentUser.getId())
                .andClubIdEqualTo(clubId);
            List<CmsUserClubRel> userClubRels = userClubRelMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(userClubRels)) {
                Asserts.forbidden();
            }

            CmsUserClubRel userClubRel = userClubRels.get(0);
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            CheckClubAuth annotation = signature.getMethod().getAnnotation(CheckClubAuth.class);
            if (!annotation.value().equals(userClubRel.getRoleId().toString())) {
                Asserts.forbidden("CheckAuth failed, current role: " + userClubRel.getRoleId()
                    + " required role: " + annotation.value());
            }
            // just need to find one
            break;
        }

        log.info("Authorized user: {}", currentUser.getUsername());
        return joinPoint.proceed();
    }

    @Pointcut("@annotation(edu.fzu.zhishe.core.annotation.IsAdmin)")
    public void checkAdminAuth() { }

    @Around("checkAdminAuth()")
    public Object checkAdminAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        SysUser currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }
        if (currentUser.getIsAdmin().equals(0)) {
            Asserts.forbidden();
        }

        log.info("Authorized admin user: {}", currentUser.getUsername());
        return joinPoint.proceed();
    }

    @Pointcut("@annotation(edu.fzu.zhishe.core.annotation.IsClubMember)")
    public void checkClubMemberAuth() { }

    @Around("checkClubMemberAuth()")
    public Object checkClubMemberAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        SysUser currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            Asserts.unAuthorized();
        }

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        for (int i = 0; i < codeSignature.getParameterNames().length; i++) {
            if (!"CLUBID".equals(codeSignature.getParameterNames()[i].toUpperCase())) {
                continue;
            }
            CmsUserClubRelExample example = new CmsUserClubRelExample();
            example.createCriteria()
                .andUserIdEqualTo(currentUser.getId())
                .andClubIdEqualTo((Integer) joinPoint.getArgs()[i]);
            List<CmsUserClubRel> userClubRels = userClubRelMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(userClubRels)) {
                Asserts.forbidden();
            }

            CmsUserClubRel userClubRel = userClubRels.get(0);
            //MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //IsClubMember annotation = signature.getMethod().getAnnotation(IsClubMember.class);
            Set<String> values = new HashSet<>(Arrays.asList("2", "3"));
            if (!values.contains(userClubRel.getRoleId().toString())) {
                Asserts.fail("CheckAuth failed, current role: " + userClubRel.getRoleId()
                    + "required role: " + values);
            }
            // just need to find one
            break;
        }

        log.info("Authorized club member: {}", currentUser.getUsername());
        return joinPoint.proceed();
    }
}
