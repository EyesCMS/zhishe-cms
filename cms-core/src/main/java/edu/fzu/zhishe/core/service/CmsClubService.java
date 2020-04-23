package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApply;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandParam;

import java.util.List;
/**
 *社团管理服务层
 *
 * @author yang
 */
public interface CmsClubService {
    CmsClubCreateApply clubCreate(CmsClubsCreationsParam clubsCreationsParam);

    List<CmsClubCreateApply> getClubCreateList();

    CmsClubCreateApply clubCreationsAudit(CmsClubsCreationsAuditParam cmsClubsCreationsAuditParam);

    CmsClubDisbandApply clubDisband(CmsClubsDisbandParam clubsDisbandParam);

    List<CmsClubDisbandApply> getClubDisbandList();

    CmsClubDisbandApply clubDissolutionsAudit(CmsClubsDisbandAuditParam cmsClubsDisbandAuditParam);
}
