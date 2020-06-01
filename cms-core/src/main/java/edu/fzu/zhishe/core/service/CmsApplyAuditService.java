package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsChiefChangeApply;
import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApply;
import edu.fzu.zhishe.cms.model.CmsClubJoinApply;
import edu.fzu.zhishe.cms.model.CmsOfficialChangeApply;
import edu.fzu.zhishe.cms.model.CmsQuitNotice;
import edu.fzu.zhishe.core.dto.CmsClubsCertificationsDTO;
import edu.fzu.zhishe.core.dto.CmsClubsChiefChangeDTO;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsDTO;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandDTO;
import edu.fzu.zhishe.core.dto.CmsClubsJoinDTO;
import edu.fzu.zhishe.core.dto.CmsClubsQuitDTO;
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
import edu.fzu.zhishe.core.param.PaginationParam;
import java.util.List;

/**
 * @author liang on 5/4/2020.
 * @version 1.0
 */
public interface CmsApplyAuditService {

    int createClub(CmsClubsCreationsParam clubsCreationsParam);

    List<CmsClubsCreationsDTO> listClubCreationApply(
        CmsClubsCreationsQuery cmsClubsCreationsQuery, PaginationParam queryParam);

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubsDisbandDTO> listClubDisbandApply(
        CmsClubsDisbandQuery cmsClubsDisbandQuery, PaginationParam queryParam);

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    List<CmsClubsJoinDTO> listJoinClubApply(
        Integer clubId, CmsClubsJoinQuery cmsClubsJoinQuery, PaginationParam paginationParam);

    CmsClubJoinApply clubJoinsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsQuitNotice clubQuit(CmsClubsQuitParam cmsClubsQuitParam);

    List<CmsClubsQuitDTO> listClubQuit(Integer clubId, CmsClubsQuitQuery cmsClubsQuitQuery,
        PaginationParam paginationParam);

    CmsChiefChangeApply clubChiefChange(CmsClubsChiefChangeParam cmsClubsChiefChangeParam);

    List<CmsClubsChiefChangeDTO> listClubChiefChangeApply(
        CmsClubsChiefChangeQuery cmsClubsChiefChangeQuery, PaginationParam queryParam);

    CmsChiefChangeApply clubChiefChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsOfficialChangeApply clubOfficialChange(CmsClubsCertificationsParam certificationsParam);

    List<CmsClubsCertificationsDTO> listClubOfficialChange(
        CmsClubsCertificationsQuery cmsClubsCertificationsQuery, PaginationParam queryParam);

    CmsOfficialChangeApply clubOfficialChangeAudit(CmsClubsAuditParam cmsClubsAuditParam);

    List<CmsOfficialChangeApply> listMyClubOfficialChange(Integer clubId, PaginationParam queryParam);

    List<CmsChiefChangeApply> listMyClubChiefChange(Integer clubId, PaginationParam queryParam);

    List<CmsClubDisbandApply> listMyClubDissolution(Integer clubId, PaginationParam queryParam);

    void updateExpiredApply();

    /**
     * pending -> rejected
     */
    int updateExpiredJoinClubApply();

    /**
     * pending -> rejected
     */
    int updateExpiredClubCreationApply();

    /**
     * pending -> rejected
     */
    int updateExpiredClubDisbandApply();

    /**
     * pending -> rejected
     */
    int updateExpiredChiefChangeApply();

    /**
     * pending -> rejected
     */
    int updateExpiredOfficialChangeApply();
}
