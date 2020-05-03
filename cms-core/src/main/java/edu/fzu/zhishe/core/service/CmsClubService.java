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
import java.util.List;

/**
 *社团管理服务层
 *
 * @author yang
 */
public interface CmsClubService {

    boolean isClubMember(Integer clubId);

    ClubStatueEnum getClubStatue(Integer clubId);

    CmsClubCreateApply createClub(CmsClubsCreationsParam clubsCreationsParam);

    List<CmsClubsCreationsDTO> listClubCreationApply(
        CmsClubsCreationsQuery cmsClubsCreationsQuery, PaginationParam queryParam);

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubsDisbandDTO> listClubDisbandApply(CmsClubsDisbandQuery cmsClubsDisbandQuery, PaginationParam queryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    List<CmsClubsJoinDTO> listJoinClubApply(Integer clubId, CmsClubsJoinQueryParam cmsClubsJoinQueryParam,QueryParam queryParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    List<CmsClubsQuitDTO> listClubQuit(Integer clubId, CmsClubsQuitQuery cmsClubsQuitQuery,
        PaginationParam queryParam);

    CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam);

    List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(
        CmsClubsChiefChangeQuery cmsClubsChiefChangeQuery, PaginationParam queryParam);

    CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsOfficialChangeApply clubOfficialChange(CmsClubsCertificationsParam certificationsParam);

    List<CmsClubsCertificationsDTO> listClubOfficialChange(
        CmsClubsCertificationsQuery cmsClubsCertificationsQuery, PaginationParam queryParam);

    CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);


    /**
     * @author wjh674
     */

    List<CmsClubBriefDTO> listHotClub(PaginationParam paginationParam, OrderByParam orderByParam);

    List<CmsClubBriefDTO> listClub(Integer page, Integer limit, String sort, String order, String keyword,String type, Integer state);

    CmsClubDetailDTO getClubById(Integer id);

    List<CmsClubBriefDTO> listJoinedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    List<CmsClubBriefDTO> listManagedClub(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    List<CmsClubJoinApplyDTO> listJoinClubApply(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    List<CmsClubCreateApplyDTO> listCreateClubApply(PaginationParam paginationParam, OrderByParam orderByParam, Integer userId);

    List<CmsClubMemberBriefDTO> listClubMember(PaginationParam paginationParam, OrderByParam orderByParam, Integer clubId);

    CmsClubMemberDetailDTO showClubMemberInfo(Integer clubId, Integer userId);

    Integer addClubMember(Integer clubId, Integer userId);

    Integer deleteClubMember(Integer clubId, Integer userId);

    Integer alterClubSlogan(Integer clubId, Integer userId, String slogan);

    Integer alterClubQqGroup(Integer clubId, Integer userId, String qqGroup);

    Integer alterClubType(Integer clubId, Integer userId, String type);

    Integer alterClubAvatarUrl(Integer clubId, Integer userId, String avatarUrl);

    /**
     * @author PSF
     */
    void activityApply(CmsClubActivityParam param);

    void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role);

    void delActivity(Integer id);

    List<CmsActivityApplyDTO> listActivitiesApply(Integer clubId,
        PaginationParam paginationParam, OrderByParam orderByParam);

    CmsActivity getActivityApplyItem(Integer id);

    void updateActivity(Integer id, CmsActivityUpdateParam param);

    List<CmsActivityApplyListDTO> listActivitiesApply(CmsActivityQuery param,
        PaginationParam paginationParam, OrderByParam orderByParam);
}
