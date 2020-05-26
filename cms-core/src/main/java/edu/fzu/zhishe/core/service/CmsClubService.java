package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.dto.*;

import edu.fzu.zhishe.core.param.CmsClubInfoParam;
import edu.fzu.zhishe.core.param.CmsClubMemberQuery;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import java.util.List;

/**
 *社团管理服务层
 *
 * @author wjh674
 */
public interface CmsClubService {

    boolean isClubMember(Integer clubId);

    ClubStatueEnum getClubStatue(Integer clubId);

    /**
     * 获取推荐社团列表
     */
    List<CmsClubBriefDTO> listHotClub(PaginationParam paginationParam, OrderByParam orderByParam);

    /**
     * 获取所有社团列表
     */
    List<CmsClubBriefDTO> listClub(PaginationParam paginationParam, OrderByParam orderByParam, String keyword, String type, Integer state);

    /**
     * 按照社团ID搜索社团
     */
    CmsClubDetailDTO getClubById(Integer id);

    /**
     * 获取学生加入的社团列表（身份为社员）
     */
    List<CmsClubBriefDTO> listJoinedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    /**
     * 获取学生管理的社团列表（身份为社长）
     */
    List<CmsClubBriefDTO> listManagedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    /**
     * 获取学生加入社团申请列表
     */
    List<CmsClubJoinApplyDTO> listJoinClubApply(PaginationParam paginationParam, OrderByParam orderByParam);

    /**
     * 获取学生创建社团申请列表
     */
    List<CmsClubCreateApplyDTO> listCreateClubApply(PaginationParam paginationParam, OrderByParam orderByParam);

    /**
     * 获取社团成员列表
     */
    List<CmsClubMemberBriefDTO> listClubMember(PaginationParam paginationParam, Integer clubId, CmsClubMemberQuery clubMemberQuery);

    /**
     * 获取社团成员详细信息
     */
    CmsClubMemberDetailDTO getClubMemberInfo(Integer clubId, Integer userId);

    /**
     * 添加社团成员
     */
    Integer addClubMember(Integer clubId, Integer userId);

    /**
     * 删除社团成员
     */
    Integer deleteClubMember(Integer clubId, Integer userId);

    /**
     * 修改社团信息
     */
    Integer updateClubInfo(Integer clubId, CmsClubInfoParam clubInfoParam);

    /**
     * 修改社团头像
     */
    Integer updateClubAvatarUrl(Integer clubId, String avatarUrl);

    /**
     * 获取社团走马灯图片
     */
    String[] getClubPicture(Integer clubId);

    /**
     * 修改社团走马灯图片
     */
    Integer updateClubPictureUrl(Integer clubId, String[] avatarUrl);
}
