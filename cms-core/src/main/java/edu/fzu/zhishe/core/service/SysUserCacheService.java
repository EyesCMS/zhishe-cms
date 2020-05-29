package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.SysUser;

/**
 * 后台用户缓存操作类
 *
 * @author liang
 * @date 2020/4/20
 */
public interface SysUserCacheService {

    /**
     * 删除用户缓存
     */
    public void delUser(Integer userId);

    /**
     * 获取缓存用户信息
     */
    SysUser getUser(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setUser(SysUser user);

    /**
     * 设置验证码
     */
    void setAuthCode(String email, String authCode);

    /**
     * 获取验证码
     */
    String getAuthCode(String email);

}
