package edu.fzu.zhishe.core.config;

import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * mall-security 模块相关配置
 *
 * @author liang
 * @date 2020/4/19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CmsSecurityConfig extends SecurityConfig {

    @Autowired
    private SysUserService userService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }

//    @Bean
//    public DynamicSecurityService dynamicSecurityService() {
//        return () -> {
//            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
//            List<SysResource> resourceList = resourceService.listAll();
//            for (SysResource resource : resourceList) {
//                map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
//            }
//            return map;
//        };
//    }
}
