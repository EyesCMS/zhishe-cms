package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.dto.*;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

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

    CommonList getClubDisbandList(QueryParam queryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    CommonList getClubJoinsList(Integer clubId,QueryParam queryParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    CommonList getClubQuitList(Integer clubId,QueryParam queryParam);

    CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam);

    CommonList getClubChiefChangeList(QueryParam queryParam);

    CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsOfficialChangeApply clubOfficialChange(CmsCertificationsParam certificationsParam);

    CommonList getClubOfficialChangeList(QueryParam queryParam);

    CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);


    List<CmsClub> getHotClubList(Integer page, Integer limit);

    List<CmsClub> getClubList(Integer page, Integer limit, String sort, String order);

    List<CmsClub> searchClubByKeyword(Integer page, Integer limit, String sort, String order, String keyword);

    List<CmsClub> searchClubById(Integer id);

    List<CmsClub> searchJoinedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClub> searchManagedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClub> searchJoinedApplyList(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClub> searchCreateApplyList(Integer page, Integer limit, String sort, String order, Integer userId);

    /**
     * @author PSF
     */
    void ativityApply(CmsClubActivityParam param);

    void activityStateChange(Integer applyId, Integer stateId, String role);

    void delActivity(Integer id);
}
