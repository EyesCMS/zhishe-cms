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
     * 删除用户资源列表缓存
     */
    //void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关用户缓存
     */
    //void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关用户缓存
     */
    //void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目用户缓存
     */
    //void delResourceListByResource(Long resourceId);

    /**
     * 获取缓存用户信息
     */
    SysUser getUser(String username);

    /**
     * 设置缓存后台用户信息
     */
    void setUser(SysUser user);

    /**
     * 获取缓存后台用户资源列表
     */
    //List<SysResource> getResourceList(Long adminId);

    /**
     * 设置后台后台用户资源列表
     */
    //void setResourceList(Long adminId, List<UmsResource> resourceList);
}
