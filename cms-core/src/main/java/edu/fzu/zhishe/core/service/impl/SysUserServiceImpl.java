package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import edu.fzu.zhishe.cms.model.SysPermission;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.util.FieldUtil;
import edu.fzu.zhishe.core.constant.UpdatePasswordResultEnum;
import edu.fzu.zhishe.core.dao.SysRolePermissionDAO;
import edu.fzu.zhishe.core.domain.SysUserDetails;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.param.SysUserRegisterParam;
import edu.fzu.zhishe.core.param.SysUserUpdateParam;
import edu.fzu.zhishe.core.param.UpdateUserPasswordParam;
import edu.fzu.zhishe.core.service.SysUserCacheService;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.cms.mapper.SysUserMapper;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.cms.model.SysUserExample;
import edu.fzu.zhishe.security.util.JwtTokenUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

//import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author liang on 4/19/2020.
 * @version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRolePermissionDAO rolePermissionDAO;
    @Autowired
    private SysUserCacheService userCacheService;
//    @Value("${redis.key.authCode}")
//    private String REDIS_KEY_PREFIX_AUTH_CODE;
//    @Value("${redis.expire.authCode}")
//    private Long AUTH_CODE_EXPIRE_SECONDS;

    public static final String ANON_USER = "anonymousUser";

    @Override
    public SysUser getByUsername(String username) {
        SysUser user = userCacheService.getUser(username);
        if (user != null) {
            //LOGGER.info("from redis get user");
            return user;
        }
        //SysUser user;
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> userList = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
            userCacheService.setUser(user);
            return user;
        }
        return null;
    }

    @Override
    public SysUser getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser register(SysUserRegisterParam userRegisterParam) {
        // 查询是否已有该用户
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(userRegisterParam.getUsername());
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(sysUsers)) {
            Asserts.fail(" 该用户名已经存在 ");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userRegisterParam, sysUser);
        sysUser.setPassword(passwordEncoder.encode(userRegisterParam.getPassword()));
        sysUser.setIsAdmin(0);
        userMapper.insert(sysUser);
        return sysUser;
    }

    @Override
    public String generateAuthCode(String telephone) {
        return null;
    }

    @Override
    public List<SysPermission> listPermissionByRoleId(Integer roleId) {
        return rolePermissionDAO.listPermissionByRoleId(roleId);
    }

    @Override
    public UpdatePasswordResultEnum updatePassword(UpdateUserPasswordParam param) {
        // 判断更新的密码长度，密码长度为6-20，不符合为不更新密码
        if (param.getNewPassword().length() < 6 || param.getNewPassword().length() > 20) {
            return UpdatePasswordResultEnum.UPDATE_ERROR;
        }

        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<SysUser> users = userMapper.selectByExample(example);
        if (CollUtil.isEmpty(users)) {
            return UpdatePasswordResultEnum.USER_NOT_FOUND;
        }

        SysUser user = users.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), user.getPassword())) {
            return UpdatePasswordResultEnum.ERROR_OLD_PASSWORD;
        }
        user.setPassword(passwordEncoder.encode(param.getNewPassword()));
        if (userMapper.updateByPrimaryKey(user) == 0) {
            return UpdatePasswordResultEnum.UPDATE_ERROR;
        }
        userCacheService.delUser(user.getId());
        return UpdatePasswordResultEnum.SUCCESS;
    }

    @Override
    public SysUser getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth.getPrincipal() == ANON_USER) {
            return null;
        }
        SysUserDetails userDetails = (SysUserDetails) auth.getPrincipal();
        return userDetails.getSysUser();
    }

//    @Override
//    public void updateIntegration(Integer id, Integer integration) {
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = getByUsername(username);
        if (sysUser != null) {
            List<SysPermission> permissionList = this.listPermissionByRoleId(sysUser.getCurrentRole());
            return new SysUserDetails(sysUser, permissionList);
        }
        throw new UsernameNotFoundException(" 用户名或密码错误 ");
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException(" 密码不正确 ");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn(" 登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public List<SysUser> users() {
        return userMapper.selectByExample(null);
    }

    @Override
    public void updateUserByParam(SysUserUpdateParam updateParam) {
        SysUser user = getCurrentUser();
        if (user == null) {
            Asserts.fail("请登录后修改信息");
        }

        Asserts.hasFiled(updateParam);

        SysUser updatedUser = new SysUser() {{
            setId(user.getId());
        }};
        BeanUtils.copyProperties(updateParam, updatedUser);

        if (userMapper.updateByPrimaryKeySelective(updatedUser) == 0) {
            Asserts.fail("修改信息更新数据库出现错误");
        }
        userCacheService.delUser(updatedUser.getId());
    }

    @Override
    public int updateUserSelective(SysUser user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        userCacheService.delUser(user.getId());
        return result;
    }

    @Override
    public UpdatePasswordResultEnum updateUserPasswordAfterAnswer(SysUserUpdatePwdByAnswer param) {
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUsernameEqualTo(param.getUsername());

        List<SysUser> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()) {
            Asserts.fail("没有该用户，更新失败");
        }
        SysUser user = users.get(0);
        if (user == null || user.getLoginAnswer() == null) {
            Asserts.fail("没有该用户或未设置密保答案, 更新失败");
        }
        if (user.getLoginAnswer().equals(param.getAnswer())) {
            // 判断更新的密码长度，密码长度为6-20，不符合为不更新密码
            if (param.getPassword().length() < 6 || param.getPassword().length() > 20) {
                Asserts.fail("新密码长度大于等于6，小于等于20，更新失败");
            }
            user.setPassword(passwordEncoder.encode(param.getPassword()));

            if (userMapper.updateByPrimaryKeySelective(user) != 0) {
                System.out.println(user.getPassword());
                userCacheService.delUser(user.getId());
                return UpdatePasswordResultEnum.SUCCESS;
            }
            return UpdatePasswordResultEnum.UPDATE_ERROR;
        } else {
            return UpdatePasswordResultEnum.ANSWER_ERROR;
        }
    }
}
