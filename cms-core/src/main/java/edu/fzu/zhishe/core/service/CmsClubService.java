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
    CmsClubCreateApply clubCreate(CmsClubsCreationsParam clubsCreationsParam);

    CommonList getClubCreateList(QueryParam queryParam);

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    CommonList getClubDisbandList(QueryParam queryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    CommonList getClubJoinsList(Integer clubId,QueryParam queryParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    CommonList getClubQuitList(Integer clubId,QueryParam queryParam);

    List<CmsClub> getHotClubList(Integer page, Integer limit);

    List<CmsClub> getClubList(Integer page, Integer limit);

    /**
     * @author PSF
     */
    void AtivityApply(CmsClubActivityParam param);

    void ActivityStateChange(Integer applyId, Integer stateId, String role);
}
