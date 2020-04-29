package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.*;

import java.util.List;

/**
 *社团管理服务层
 *
 * @author yang
 */
public interface CmsClubService {
    CmsClubCreateApply createClub(CmsClubsCreationsParam clubsCreationsParam);

    List<CmsClubCreateApply> listClubCreationApply(CmsClubCreationQueryParam queryParam, Integer page, Integer limit);

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubDisbandApply> listClubDisbandApply(QueryParam queryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    CommonList listJoinClubApply(Integer clubId,QueryParam queryParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    CommonList listClubQuit(Integer clubId,QueryParam queryParam);

    CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam);

    CommonList listClubChiefChangeApply(QueryParam queryParam);

    CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsOfficialChangeApply clubOfficialChange(CmsCertificationsParam certificationsParam);

    CommonList getClubOfficialChangeList(QueryParam queryParam);

    CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);


    List<CmsClub> listHotClub(Integer page, Integer limit);

    List<CmsClub> listClub(Integer page, Integer limit, String sort, String order, String keyword);

    List<CmsClub> getClubById(Integer id);

    List<CmsClub> listJoinedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClub> listManagedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClub> listJoinClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClub> listCreateClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    /**
     * @author PSF
     */
    void ativityApply(CmsClubActivityParam param);

    void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role);

    void delActivity(Integer id);
}
