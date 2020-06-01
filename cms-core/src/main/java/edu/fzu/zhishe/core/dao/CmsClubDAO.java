package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.param.CmsClubMemberQuery;
import edu.fzu.zhishe.core.param.OrderByParam;
import java.util.List;

import edu.fzu.zhishe.core.dto.*;
import org.apache.ibatis.annotations.Param;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public interface CmsClubDAO {

    /**
     * 获取推荐社团列表
     * @param orderByParam 排序规则参数
     * @return List<CmsClubBriefDTO> if success
     */
    List<CmsClubBriefDTO> listHotClub(@Param("orderBy") OrderByParam orderByParam);

    /**
     * 获取所有社团列表/模糊搜索社团
     * @param keyword 社团名称关键字
     * @param type 社团类型
     * @param state 社团（删除）状态
     * @return List<CmsClubBriefDTO> if success
     */
    List<CmsClubBriefDTO> listClub(@Param("keyword") String keyword,@Param("type") String type,
                                   @Param("state") Integer state);

    /**
     * 加入社团列表
     */
    List<CmsClubBriefDTO> listJoinedClub(@Param("orderBy") OrderByParam orderByParam, @Param("userId") Integer userId);

    /**
     * 管理社团列表
     */
    List<CmsClubBriefDTO> listManagedClub(@Param("orderBy") OrderByParam orderByParam, @Param("userId") Integer userId);

    /**
     * 加入社团申请列表
     */
    List<CmsClubJoinApplyDTO> listJoinClubApply(@Param("orderBy") OrderByParam orderByParam, @Param("userId") Integer userId);

    /**
     * 创建社团申请列表
     */
    List<CmsClubCreateApplyDTO> listCreateClubApply(@Param("orderBy") OrderByParam orderByParam, @Param("userId") Integer userId);

    List<CmsClubMemberBriefDTO> listClubMember(@Param("clubId") Integer clubId, @Param("queryParam") CmsClubMemberQuery clubMemberQuery);
}
