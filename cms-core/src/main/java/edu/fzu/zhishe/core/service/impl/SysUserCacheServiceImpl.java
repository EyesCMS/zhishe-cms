package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.core.service.SysUserCacheService;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.security.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author liang on 4/21/2020.
 * @version 1.0
 */
@Service
public class SysUserCacheServiceImpl implements SysUserCacheService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.user}")
    private String REDIS_KEY_USER;
    @Value("${redis.key.authCode}")
    private String REDIS_KEY_AUTH_CODE;
    @Value("${redis.expire.authCode}")
    private Long REDIS_EXPIRE_AUTH_CODE;

    @Autowired
    private SysUserService userService;

    @Override
    public void delUser(Integer userId) {
        SysUser user = userService.getById(userId);
        if (user != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public SysUser getUser(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + username;
        return (SysUser) redisService.get(key);
    }

    @Override
    public void setUser(SysUser user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_USER + ":" + user.getUsername();
        redisService.set(key, user, REDIS_EXPIRE);
    }

    @Override
    public void setAuthCode(String email, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + email;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @Override
    public String getAuthCode(String email) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + email;
        return (String) redisService.get(key);
    }
}
