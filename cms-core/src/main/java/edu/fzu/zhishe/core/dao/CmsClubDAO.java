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
    List<CmsClub> getClubList(@Param("offset") Integer offset, @Param("limit") Integer limit,
                              @Param("sort") String sort,@Param("order") String order);

    /**
     * 模糊搜索社团
     */
    List<CmsClub> searchClubByKeyword(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                  @Param("sort") String sort, @Param("order") String order,
                                  @Param("keyword") String keyword);

    /**
     * 按ID搜索社团
     */
    List<CmsClub> searchClubById(@Param("id") Integer id);

    /**
     * 加入社团列表
     */
    List<CmsClub> searchJoinedClub(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                      @Param("sort") String sort, @Param("order") String order,
                                      @Param("userId") Integer userId);

    /**
     * 管理社团列表
     */
    List<CmsClub> searchManagedClub(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                   @Param("sort") String sort, @Param("order") String order,
                                   @Param("userId") Integer userId);
}
