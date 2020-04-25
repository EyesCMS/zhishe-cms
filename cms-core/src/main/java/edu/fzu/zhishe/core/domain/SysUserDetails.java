package edu.fzu.zhishe.core.domain;

import com.sun.media.jfxmedia.logging.Logger;
import edu.fzu.zhishe.cms.model.SysPermission;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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

    private List<SysPermission> permissionList;

    public SysUserDetails(SysUser sysUser, List<SysPermission> permissionList) {
        this.sysUser = sysUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的权限
//        List<String> authorities = new ArrayList<String>() {{
//            add("sys:user:create");
//            add("sys:user:read");
//            add("sys:user:update");
//            add("sys:user:delete");
//        }};
        // 返回当前用户的权限
        return permissionList
            .stream()
            .map(perm -> new SimpleGrantedAuthority(perm.getPermissionCode()))
            .collect(Collectors.toList());
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
