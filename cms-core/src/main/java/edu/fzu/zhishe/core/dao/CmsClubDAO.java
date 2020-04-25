package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.SysUser;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public interface CmsClubDAO {
    /*
     * 通过社团id获取社团的成员对象列表
     */
    List<SysUser> selectUserByClubId(Integer id);

    /**
     * 获取人气推荐社团
     */
    List<CmsClub> getHotClubList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获取社团列表
     */
    List<CmsClub> getClubList(@Param("offset") Integer offset, @Param("limit") Integer limit);
}
