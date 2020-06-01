package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.IsLogin;
import edu.fzu.zhishe.core.config.StorageProperties;
import edu.fzu.zhishe.core.constant.UpdatePasswordResultEnum;
import edu.fzu.zhishe.core.domain.SysUserDetails;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.error.DatabaseErrorEnum;
import edu.fzu.zhishe.core.error.UserErrorEnum;
import edu.fzu.zhishe.core.param.SysRegisterParam;
import edu.fzu.zhishe.core.param.SysUserRegisterParam;
import edu.fzu.zhishe.core.param.SysUserUpdateParam;
import edu.fzu.zhishe.core.param.UpdateUserPasswordParam;
import edu.fzu.zhishe.core.service.MailService;
import edu.fzu.zhishe.core.service.StorageService;
import edu.fzu.zhishe.core.service.SysUserCacheService;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.cms.mapper.SysUserMapper;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.cms.model.SysUserExample;
import edu.fzu.zhishe.security.util.JwtTokenUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liang on 4/19/2020.
 * @version 1.0
 */
@Slf4j
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
    private SysUserCacheService userCacheService;

    @Value("${zhishe.base_url}")
    private String baseUrl;
    @Value("${zhishe.default.nickname}")
    private String defaultNickname;
    @Value("${zhishe.default.avatar_url}")
    private String defaultAvatarUrl;

    @Autowired
    StorageService storageService;

    private final Path imageRootLocation;

    @Autowired
    public SysUserServiceImpl(StorageProperties storageProperties) {
        this.imageRootLocation = Paths.get(storageProperties.getImageLocation());
    }

    @Autowired
    MailService mailService;

    /**
     * 验证码长度
     */
    private static final int CODE_SIZE = 6;

    /**
     * 验证码范围
     */
    private static final int CODE_BOUND = 10;


    public static final String EMAIL_SUBJECT = "Zhishe 注册";

    @Override
    public SysUser getByUsername(String username) {
        SysUser user = userCacheService.getUser(username);
        if (user != null) {
            return user;
        }
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> userList = userMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(userList)) {
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
    public int register(SysRegisterParam registerParam) {
        if (!verifyAuthCode(registerParam.getEmail(), registerParam.getAuthCode())) {
            Asserts.fail(UserErrorEnum.AUTH_CODE_MISMATCH);
        }

        // 验证是否存在相同用户名(邮箱已通过验证码验证）
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(registerParam.getUsername());
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(sysUsers)) {
            Asserts.fail(UserErrorEnum.DUPLICATE_USERNAME);
        }
        // 添加操作
        SysUser sysUser = new SysUser();
        sysUser.setUsername(registerParam.getUsername());
        sysUser.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        sysUser.setEmail(registerParam.getEmail());
        sysUser.setNickname(defaultNickname);
        sysUser.setAvatarUrl(defaultAvatarUrl);
        sysUser.setIsAdmin(0);
        sysUser.setRegisterDate(new Date());
        return userMapper.insertSelective(sysUser);
    }

    /**
     * 校验注册验证码
     * @param email 邮箱
     * @param authCode 验证码
     * @return
     */
    private boolean verifyAuthCode(String email, String authCode) {
        if (StrUtil.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = userCacheService.getAuthCode(email);
        return authCode.equals(realAuthCode);
    }

    @Override
    public void generateAuthCode(String email) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andEmailEqualTo(email);
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(sysUsers)) {
            Asserts.fail(UserErrorEnum.DUPLICATE_EMAIL);
        }

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CODE_SIZE; i++) {
            stringBuilder.append(random.nextInt(CODE_BOUND));
        }
        String code = stringBuilder.toString();
        userCacheService.setAuthCode(email, code);
        String content = "<html>\n" +
            "<body>\n" +
            "<div style=\"border: black;width: 400px;height: 400px;\">\n" +
            "<p> 尊敬的先生/女生，您好！</p>\n" +
            "<p>     您的注册验证码为：<b>" + code + "</b></p>\n" +
            "<p>     验证码有效时间为 10 分钟</p><br/>\n" +
            "<p> <a href=\"https://github.com/eyescms\">The Zhishe Team</a></p>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
        mailService.sendHtmlMail(email, EMAIL_SUBJECT, content);
    }

    @Override
    public UpdatePasswordResultEnum updatePassword(UpdateUserPasswordParam param) {

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
        Object principal = context.getAuthentication().getPrincipal();
        if (principal.getClass().equals(SysUserDetails.class)) {
            return ((SysUserDetails) principal).getSysUser();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = getByUsername(username);
        if (sysUser != null) {
            return new SysUserDetails(sysUser);
        }
        throw new UsernameNotFoundException(UserErrorEnum.BAD_USERNAME_OR_PASSWORD.getMessage());
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException(UserErrorEnum.BAD_PASSWORD.getMessage());
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
    public List<SysUser> users() {
        return userMapper.selectByExample(null);
    }

    @IsLogin
    @Override
    public void updateUserByParam(SysUserUpdateParam updateParam) {

        Asserts.hasFiled(updateParam);

        SysUser updatedUser = new SysUser();
        updatedUser.setId(getCurrentUser().getId());
        BeanUtils.copyProperties(updateParam, updatedUser);

        if (userMapper.updateByPrimaryKeySelective(updatedUser) == 0) {
            Asserts.fail(DatabaseErrorEnum.UPDATE_ERROR);
        }
        userCacheService.delUser(updatedUser.getId());
    }

    @Override
    public String updateAvatar(MultipartFile avatarImg) {

        SysUser currentUser = this.getCurrentUser();
        String avatarUrl = currentUser.getAvatarUrl();
        String rootLocation = baseUrl + "/files/images";
        if (avatarUrl != null) {
            // delete if avatar is uploaded to server before
            int index = avatarUrl.lastIndexOf(StrUtil.SLASH);
            if (rootLocation.equals(avatarUrl.substring(0, index))) {
                String filename = avatarUrl.substring(index);
                Path oldAvatarPath = Paths.get(imageRootLocation.toAbsolutePath() + filename);
                storageService.deleteFile(oldAvatarPath);
            }
        }

        // 1. upload avatar
        String url = storageService.store(avatarImg, imageRootLocation);
        log.info("You successfully uploaded " + avatarImg.getOriginalFilename() + "!");

        // 2. update user info
        SysUser user = new SysUser();
        user.setId(currentUser.getId());
        user.setAvatarUrl(url);
        if (userMapper.updateByPrimaryKeySelective(user) == 0) {
            Asserts.fail("update avatar failed");
        }
        userCacheService.delUser(user.getId());
        return url;
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
        if (CollUtil.isEmpty(users)) {
            Asserts.fail(UserErrorEnum.USERNAME_NOT_FOUND);
        }
        SysUser user = users.get(0);
        if (user.getLoginAnswer() == null) {
            Asserts.fail(UserErrorEnum.NO_LOGIN_ANSWER);
        }
        if (user.getLoginAnswer().equals(param.getAnswer())) {
            user.setPassword(passwordEncoder.encode(param.getPassword()));

            if (userMapper.updateByPrimaryKeySelective(user) != 0) {
                userCacheService.delUser(user.getId());
                return UpdatePasswordResultEnum.SUCCESS;
            }
            return UpdatePasswordResultEnum.UPDATE_ERROR;
        } else {
            return UpdatePasswordResultEnum.ANSWER_ERROR;
        }
    }
}
