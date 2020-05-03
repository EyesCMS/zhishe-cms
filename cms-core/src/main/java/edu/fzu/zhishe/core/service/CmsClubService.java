package edu.fzu.zhishe.core.service;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.*;
import org.springframework.web.bind.annotation.RequestParam;

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

    List<CmsClubsCreationsDTO> listClubCreationApply(CmsClubsCreationsQueryParam cmsClubsCreationsQueryParam,QueryParam queryParam);

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubsDisbandDTO> listClubDisbandApply(CmsClubsDisbandQueryParam cmsClubsDisbandQueryParam,QueryParam queryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    List<CmsClubsJoinDTO> listJoinClubApply(Integer clubId, CmsClubsJoinQueryParam cmsClubsJoinQueryParam,QueryParam queryParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    List<CmsClubsQuitDTO> listClubQuit(Integer clubId,CmsClubsQuitQueryParam cmsClubsQuitQueryParam,QueryParam queryParam);

    CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam);

    List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(CmsClubsChiefChangeQueryParam cmsClubsChiefChangeQueryParam,QueryParam queryParam);

    CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsOfficialChangeApply clubOfficialChange(CmsClubsCertificationsParam certificationsParam);

    List<CmsClubsCertificationsDTO> listClubOfficialChange(CmsClubsCertificationsQueryParam cmsClubsCertificationsQueryParam,QueryParam queryParam);

    CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);


    /**
     * @author wjh674
     */

    List<CmsClubReturnData1> listHotClub(Integer page, Integer limit, String sort, String order);

    List<CmsClubReturnData1> listClub(Integer page, Integer limit, String sort, String order, String keyword);

    CmsClubReturnData2 getClubById(Integer id);

    List<CmsClubReturnData1> listJoinedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubReturnData1> listManagedClub(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubReturnData3> listJoinClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubReturnData4> listCreateClubApply(Integer page, Integer limit, String sort, String order, Integer userId);

    List<CmsClubReturnData5> listClubMember(Integer page, Integer limit, String sort, String order, Integer clubId);

    CmsClubReturnData6 showClubMemberInfo(Integer clubId, Integer userId);

    Integer addClubMember(Integer clubId, Integer userId);

    Integer deleteClubMember(Integer clubId, Integer userId);

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

    List<CmsAtivityApplyListDTO> listActivitiesApply(CmsActivitySearchParam param,
        Integer page, Integer limit, String sort, String order);
}
