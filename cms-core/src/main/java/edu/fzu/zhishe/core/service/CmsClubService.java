package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.*;

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

    List<CmsClubCreateApply> listClubCreationApply(CmsClubCreationQueryParam queryParam, Integer page, Integer limit);

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubsDisbandDTO> listClubDisbandApply(CmsClubsDisbandQueryParam cmsClubsDisbandQueryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    List<CmsClubsJoinDTO> listJoinClubApply(Integer clubId, CmsClubsJoinQueryParam cmsClubsJoinQueryParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    List<CmsClubsQuitDTO> listClubQuit(Integer clubId,CmsClubsQuitQueryParam cmsClubsQuitQueryParam);

    CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam);

    List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(CmsClubsChiefChangeQueryParam cmsClubsChiefChangeQueryParam);

    CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsOfficialChangeApply clubOfficialChange(CmsClubsCertificationsParam certificationsParam);

    List<CmsClubsCertificationsDTO> listClubOfficialChange(CmsClubsCertificationsQueryParam cmsClubsCertificationsQueryParam);

    CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);


    List<CmsClubReturnData1> listHotClub(Integer page, Integer limit, String sort, String order);

    List<CmsClubReturnData1> listClub(Integer page, Integer limit, String sort, String order, String keyword);

    CmsClubReturnData2 getClubById(Integer id);

    List<CmsClubReturnData1> listJoinedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubReturnData1> listManagedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubReturnData3> listJoinClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubReturnData4> listCreateClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClub> listClubMember(Integer page, Integer limit, String sort, String order, Integer clubId);

    CmsClubReturnData6 showClubMemberInfo(Integer clubId, Integer userId);

    List<CmsClub> addClubMember(Integer clubId, Integer userId);

    /**
     * @author PSF
     */
    void activityApply(CmsClubActivityParam param);

    void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role);

    void delActivity(Integer id);

    List<CmsActivityApplyDTO> getActivitiesApply(Integer clubId);

    CmsActivity getActivityApplyItem(Integer id);
}
