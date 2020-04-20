package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.core.constant.UpdatePasswordResultEnum;
import edu.fzu.zhishe.core.dto.SysUserRegisterParam;
import edu.fzu.zhishe.core.dto.UpdateUserPasswordParam;
import edu.fzu.zhishe.cms.model.SysUser;
//import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理
 * @author liang
 */
public interface SysUserService {

    /**
     * 根据用户名获取用户
     */
    SysUser getByUsername(String username);

    /**
     * 根据用户编号获取用户
     */
    SysUser getById(Integer id);

    /**
     * 用户注册
     */
    @Transactional
    SysUser register(SysUserRegisterParam umsAdminParam);

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    UpdatePasswordResultEnum updatePassword(UpdateUserPasswordParam updateUserPasswordParam);

    /**
     * 获取当前登录
     */
    SysUser getCurrentMember();

    /**
     * 根据用户 id 修改用户积分
     */
    //void updateIntegration(Integer id, Integer integration);


    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 登录后获取 token
     */
    String login(String username, String password);

    /**
     * 刷新 token
     */
    String refreshToken(String token);

    /**
     * 获取所有用户
     */
    List<SysUser> users();
}
