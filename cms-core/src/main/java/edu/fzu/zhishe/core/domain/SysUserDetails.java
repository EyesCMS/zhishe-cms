package edu.fzu.zhishe.core.domain;

import edu.fzu.zhishe.cms.model.SysUser;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详情封装
 *
 * @author liang
 */
public class SysUserDetails implements UserDetails {

    private SysUser sysUser;

    public SysUserDetails(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的权限
        return Collections.singletonList(new SimpleGrantedAuthority("TEST"));
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return sysUser.getStatus() == 1;
        return true;
    }

    public SysUser getSysUser() {
        return sysUser;
    }
}
