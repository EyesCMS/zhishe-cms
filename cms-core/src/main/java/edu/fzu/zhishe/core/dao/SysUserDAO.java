package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.CmsClub;

import java.util.List;

/**
 *
 * @author PSF(52260506 @ qq.com)
 */
public interface SysUserDAO {
    /*
     * 通过用户id获取用户所在社团对象列表
     */
    List<CmsClub> selectClubByUserId(Integer id);
}
