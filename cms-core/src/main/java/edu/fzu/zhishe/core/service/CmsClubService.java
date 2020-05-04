package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.*;

import edu.fzu.zhishe.core.param.CmsActivityQuery;
import edu.fzu.zhishe.core.param.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.CmsClubsAuditParam;
import edu.fzu.zhishe.core.param.CmsClubsCertificationsParam;
import edu.fzu.zhishe.core.param.CmsClubsCertificationsQuery;
import edu.fzu.zhishe.core.param.CmsClubsChiefChangeParam;
import edu.fzu.zhishe.core.param.CmsClubsChiefChangeQuery;
import edu.fzu.zhishe.core.param.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.param.CmsClubsCreationsQuery;
import edu.fzu.zhishe.core.param.CmsClubsDisbandParam;
import edu.fzu.zhishe.core.param.CmsClubsDisbandQuery;
import edu.fzu.zhishe.core.param.CmsClubsJoinParam;
import edu.fzu.zhishe.core.param.CmsClubsJoinQuery;
import edu.fzu.zhishe.core.param.CmsClubsQuitParam;
import edu.fzu.zhishe.core.param.CmsClubsQuitQuery;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.param.QueryParam;
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

    List<CmsClubMemberBriefDTO> listClubMember(PaginationParam paginationParam, OrderByParam orderByParam, Integer clubId);

    CmsClubMemberDetailDTO showClubMemberInfo(Integer clubId, Integer userId);

    Integer addClubMember(Integer clubId, Integer userId);

    Integer deleteClubMember(Integer clubId, Integer userId);

    Integer alterClubSlogan(Integer clubId, Integer userId, String slogan);

    Integer alterClubQqGroup(Integer clubId, Integer userId, String qqGroup);

    Integer alterClubType(Integer clubId, Integer userId, String type);

    Integer alterClubAvatarUrl(Integer clubId, Integer userId, String avatarUrl);

}
