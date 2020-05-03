package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.cms.model.SysUser;

import java.util.List;

import edu.fzu.zhishe.core.dto.*;
import org.apache.ibatis.annotations.Param;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public interface CmsClubDAO {
    /*
     * 通过社团id获取社团的成员对象列表
     */
    List<SysUser> listClubMember(Integer id);

    /**
     * 获取人气推荐社团
     */
    List<CmsClubBriefDTO> listHotClub(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                         @Param("sort") String sort, @Param("order") String order);

    /**
     * 模糊搜索社团
     */
    List<CmsClubBriefDTO> listClub(@Param("sort") String sort, @Param("order") String order,
                                      @Param("keyword") String keyword);


    /**
     * 加入社团列表
     */
    List<CmsClubBriefDTO> listJoinedClub(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                      @Param("sort") String sort, @Param("order") String order,
                                      @Param("userId") Integer userId);

    /**
     * 管理社团列表
     */
    List<CmsClubBriefDTO> listManagedClub(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                   @Param("sort") String sort, @Param("order") String order,
                                   @Param("userId") Integer userId);

    /**
     * 加入社团申请列表
     */
    List<CmsClubJoinApplyDTO> listJoinClubApply(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                               @Param("sort") String sort, @Param("order") String order,
                                               @Param("userId") Integer userId);

    /**
     * 创建社团申请列表
     */
    List<CmsClubCreateApplyDTO> listCreateClubApply(@Param("offset") Integer offset, @Param("limit") Integer limit,
                                                 @Param("sort") String sort, @Param("order") String order,
                                                 @Param("userId") Integer userId);


}
