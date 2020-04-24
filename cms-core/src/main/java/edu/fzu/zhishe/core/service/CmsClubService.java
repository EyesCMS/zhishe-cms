package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApply;
import edu.fzu.zhishe.cms.model.CmsClubJoinApply;
import edu.fzu.zhishe.core.dto.CmsClubsAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandParam;
import edu.fzu.zhishe.core.dto.CmsClubsJoinParam;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *社团管理服务层
 *
 * @author yang
 */
public interface CmsClubService {
    CmsClubCreateApply clubCreate(CmsClubsCreationsParam clubsCreationsParam);

    List<CmsClubCreateApply> getClubCreateList();

    CmsClubCreateApply clubCreationsAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubDisbandApply> getClubDisbandList();

    CmsClubDisbandApply clubDissolutionAudit(CmsClubsAuditParam cmsClubsAuditParam);

    CmsClubJoinApply clubJoin(CmsClubsJoinParam cmsClubsJoinParam);

    List<CmsClub> hotClubList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
}
