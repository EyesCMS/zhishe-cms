package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.SysUser;

import java.util.List;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public interface CmsClubDAO {
    /*
     * 通过社团id获取社团的成员对象列表
     */
    List<SysUser> selectUserByClubId(Integer id);
}
