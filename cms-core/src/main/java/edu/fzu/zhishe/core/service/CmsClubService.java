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

    List<CmsClubBriefDTO> listHotClub(PaginationParam paginationParam, OrderByParam orderByParam);

    List<CmsClubBriefDTO> listClub(PaginationParam paginationParam, OrderByParam orderByParam, String keyword, String type, Integer state);

    CmsClubDetailDTO getClubById(Integer id);

    List<CmsClubBriefDTO> listJoinedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    List<CmsClubBriefDTO> listManagedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    List<CmsClubJoinApplyDTO> listJoinClubApply(PaginationParam paginationParam, OrderByParam orderByParam);

    List<CmsClubCreateApplyDTO> listCreateClubApply(PaginationParam paginationParam, OrderByParam orderByParam);

    List<CmsClubMemberBriefDTO> listClubMember(PaginationParam paginationParam, Integer clubId, CmsClubMemberQuery clubMemberQuery);

    CmsClubMemberDetailDTO getClubMemberInfo(Integer clubId, Integer userId);

    Integer addClubMember(Integer clubId, Integer userId);

    Integer deleteClubMember(Integer clubId, Integer userId);

    Integer updateClubInfo(Integer clubId, CmsClubInfoParam clubInfoParam);

    Integer updateClubAvatarUrl(Integer clubId, String avatarUrl);

    String[] getClubPicture(Integer clubId);

    Integer updateClubPictureUrl(Integer clubId, String[] avatarUrl);
}
