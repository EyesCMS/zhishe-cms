package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.*;

import edu.fzu.zhishe.core.param.CmsActivitySearchParam;
import edu.fzu.zhishe.core.param.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.CmsClubsAuditParam;
import edu.fzu.zhishe.core.param.CmsClubsCertificationsParam;
import edu.fzu.zhishe.core.param.CmsClubsCertificationsQueryParam;
import edu.fzu.zhishe.core.param.CmsClubsChiefChangeParam;
import edu.fzu.zhishe.core.param.CmsClubsChiefChangeQueryParam;
import edu.fzu.zhishe.core.param.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.param.CmsClubsCreationsQueryParam;
import edu.fzu.zhishe.core.param.CmsClubsDisbandParam;
import edu.fzu.zhishe.core.param.CmsClubsDisbandQueryParam;
import edu.fzu.zhishe.core.param.CmsClubsJoinParam;
import edu.fzu.zhishe.core.param.CmsClubsJoinQueryParam;
import edu.fzu.zhishe.core.param.CmsClubsQuitParam;
import edu.fzu.zhishe.core.param.CmsClubsQuitQueryParam;
import edu.fzu.zhishe.core.param.QueryParam;
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
        CmsClubsCreationsQueryParam cmsClubsCreationsQueryParam, QueryParam queryParam);

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubsDisbandDTO> listClubDisbandApply(
        CmsClubsDisbandQueryParam cmsClubsDisbandQueryParam,QueryParam queryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    List<CmsClubsJoinDTO> listJoinClubApply(Integer clubId, CmsClubsJoinQueryParam cmsClubsJoinQueryParam,QueryParam queryParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    List<CmsClubsQuitDTO> listClubQuit(Integer clubId, CmsClubsQuitQueryParam cmsClubsQuitQueryParam,QueryParam queryParam);

    CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam);

    List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(
        CmsClubsChiefChangeQueryParam cmsClubsChiefChangeQueryParam,QueryParam queryParam);

    CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsOfficialChangeApply clubOfficialChange(CmsClubsCertificationsParam certificationsParam);

    List<CmsClubsCertificationsDTO> listClubOfficialChange(
        CmsClubsCertificationsQueryParam cmsClubsCertificationsQueryParam,QueryParam queryParam);

    CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);


    /**
     * @author wjh674
     */

    List<CmsClubBriefDTO> listHotClub(Integer page, Integer limit, String sort, String order);

    List<CmsClubBriefDTO> listClub(Integer page, Integer limit, String sort, String order, String keyword,String type, Integer state);

    CmsClubDetailDTO getClubById(Integer id);

    List<CmsClubBriefDTO> listJoinedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubBriefDTO> listManagedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubJoinApplyDTO> listJoinClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubCreateApplyDTO> listCreateClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubMemberBriefDTO> listClubMember(Integer page, Integer limit, String sort, String order, Integer clubId);

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
            Integer page, Integer limit, String sort, String order);

    CmsActivity getActivityApplyItem(Integer id);

    void updateActivity(Integer id, CmsActivityUpdateParam param);

    List<CmsActivityApplyListDTO> listActivitiesApply(CmsActivitySearchParam param,
        Integer page, Integer limit, String sort, String order);
}
