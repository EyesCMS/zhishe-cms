package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.core.dto.*;
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
import edu.fzu.zhishe.core.service.CmsApplyAuditService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @version 1.0
 */
@RestController
@RequestMapping("/clubs")
public class CmsApplyAuditController {

    @Autowired
    private CmsApplyAuditService applyAuditService;

    @ApiOperation(" 4.1提交创建社团申请表单 ")
    @PostMapping("/creations")
    public ResponseEntity<Object> createClub(@Validated @RequestBody CmsClubsCreationsParam cmsClubsCreationsParam){
        applyAuditService.createClub(cmsClubsCreationsParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 测试更新权限 ")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('cms:club:update')")
    public ResponseEntity<Object> deleteClub(@PathVariable Integer id){
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.2社团创建申请列表 ")
    @GetMapping("/creations")
    public ResponseEntity<Object> clubCreateList(
            CmsClubsCreationsQuery cmsClubsCreationsQuery, @Validated PaginationParam paginationParam) {
        List<CmsClubsCreationsDTO> cmsClubsCreationsDTOList = applyAuditService.listClubCreationApply(
            cmsClubsCreationsQuery, paginationParam);
        /*id 和 userid可能不需要，如果后面真的不需要可以在model加上jsonignore，先留着*/
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsCreationsDTOList));
    }

    @ApiOperation(" 4.3审核创建社团申请 ")
    @PutMapping("/creations/audit")
    public ResponseEntity<Object> clubCreateAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        applyAuditService.clubCreationsAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.4提交解散社团申请表单 ")
    @PostMapping("/dissolution")
    public ResponseEntity<Object> clubDissolve(@Validated @RequestBody CmsClubsDisbandParam cmsClubsDisbandParam){
        applyAuditService.clubDisband(cmsClubsDisbandParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 4.5社团解散申请列表 ")
    @GetMapping("/dissolution")
    public ResponseEntity<Object> clubDisbandList(CmsClubsDisbandQuery cmsClubsDisbandQuery, @Validated PaginationParam paginationParam) {
        /*有的参数可能不需要返回，如果后面真的不需要可以在model加上jsonignore，先留着*/
        List<CmsClubsDisbandDTO> cmsClubsDisbandDTOList = applyAuditService.listClubDisbandApply(
            cmsClubsDisbandQuery, paginationParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsDisbandDTOList));
    }

    @ApiOperation(" 4.6审核解散社团申请 ")
    @PutMapping("/dissolution/audit")
    public ResponseEntity<Object> clubDisbandAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        applyAuditService.clubDissolutionAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.7提交加入社团申请表单 ")
    @PostMapping("/join")
    public ResponseEntity<Object> clubJoin(@Validated @RequestBody CmsClubsJoinParam cmsClubsJoinParam){
        applyAuditService.clubJoin(cmsClubsJoinParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = " 4.8根据社团 ID 获取申请列表 ")
    @GetMapping("/{clubId}/joins")
    public ResponseEntity<Object> joinsList(CmsClubsJoinQuery cmsClubsJoinQuery, @PathVariable("clubId") Integer clubId,
            @Validated PaginationParam paginationParam) {
        List<CmsClubsJoinDTO> cmsClubsJoinDTOList = applyAuditService
            .listJoinClubApply(clubId, cmsClubsJoinQuery, paginationParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsJoinDTOList));
    }

    @ApiOperation(" 4.9审核解散社团申请 ")
    @PutMapping("/joins/audit")
    public ResponseEntity<Object> clubJoinsAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        applyAuditService.clubJoinsAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.10提交退出社团申请表单 ")
    @PostMapping("/quit")
    public ResponseEntity<Object> clubQuit(@Validated @RequestBody CmsClubsQuitParam cmsClubsQuitParam){
        applyAuditService.clubQuit(cmsClubsQuitParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = " 4.11根据社团 ID 获取退社申请列表 ")
    @GetMapping("/{clubId}/quit")
    public ResponseEntity<Object> quitList(CmsClubsQuitQuery cmsClubsQuitQuery,
                                           @PathVariable("clubId") Integer clubId,
                                           @Validated PaginationParam paginationParam) {
        List<CmsClubsQuitDTO> cmsClubsQuitDTOList = applyAuditService.listClubQuit(clubId,
            cmsClubsQuitQuery, paginationParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsQuitDTOList));
    }

    @ApiOperation(" 4.12提交社团换届申请表单 ")
    @PostMapping("/leader/change")
    public ResponseEntity<Object> clubChiefChange(@Validated @RequestBody CmsClubsChiefChangeParam cmsClubsChiefChangeParam){
        applyAuditService.clubChiefChange(cmsClubsChiefChangeParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(" 4.13社团换届申请列表 ")
    @GetMapping("/leader/changes")
    public ResponseEntity<Object> clubChiefChangeList(
            CmsClubsChiefChangeQuery cmsClubsChiefChangeQuery, @Validated PaginationParam paginationParam){
        List<CmsClubsChiefChangeDTO> cmsClubsChiefChangeDTOList = applyAuditService.listClubChiefChangeApply(
            cmsClubsChiefChangeQuery, paginationParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsChiefChangeDTOList));
    }

    @ApiOperation(" 4.14审核社团换届申请 ")
    @PutMapping("/leader/changes")
    public ResponseEntity<Object> clubChiefChangeAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        applyAuditService.clubChiefChangeAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.15提交社团认证申请表单 ")
    @PostMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChange(@Validated @RequestBody CmsClubsCertificationsParam cmsClubsCertificationsParam){
        applyAuditService.clubOfficialChange(cmsClubsCertificationsParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(" 4.16社团认证申请列表 ")
    @GetMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChangeList(
            CmsClubsCertificationsQuery cmsClubsCertificationsQuery, @Validated PaginationParam paginationParam) {
        List<CmsClubsCertificationsDTO> cmsClubsCertificationsDTOList = applyAuditService.listClubOfficialChange(
            cmsClubsCertificationsQuery, paginationParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsCertificationsDTOList));
    }

    @ApiOperation(" 4.17审核社团认证申请 ")
    @PutMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChangeAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        // TODO: 等待api修改
        applyAuditService.clubOfficialChangeAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }
}
