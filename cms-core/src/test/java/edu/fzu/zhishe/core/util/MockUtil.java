package edu.fzu.zhishe.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MockUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static void mockLoginUser(String username) {
        UserDetails user = MockUtil.applicationContext.getBean(UserDetailsService.class)
            .loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
            user, null, AuthorityUtils
            .commaSeparatedStringToAuthorityList(null));
        SecurityContextHolder.getContext().setAuthentication(authRequest);
        System.out.println(user);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MockUtil.applicationContext = applicationContext;
    }
}
