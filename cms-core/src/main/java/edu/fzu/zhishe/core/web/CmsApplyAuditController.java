package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.core.dto.*;
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
import edu.fzu.zhishe.core.service.CmsClubService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @version 1.0
 */
@RestController
@RequestMapping("/clubs")
public class CmsApplyAuditController {

    @Autowired
    private CmsClubService clubService;

    @ApiOperation(" 4.1提交创建社团申请表单 ")
    @PostMapping("/creations")
    public ResponseEntity<Object> createClub(@Validated @RequestBody CmsClubsCreationsParam cmsClubsCreationsParam){
        clubService.createClub(cmsClubsCreationsParam);
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
        CmsClubsCreationsQueryParam cmsClubsCreationsQueryParam,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                 @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                 @RequestParam(value = "order", defaultValue = "asc") String order,
                                                 @RequestParam(value = "keyword", required = false) String keyword) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        List<CmsClubsCreationsDTO> cmsClubsCreationsDTOList = clubService.listClubCreationApply(cmsClubsCreationsQueryParam,queryParam);
        /*id 和 userid可能不需要，如果后面真的不需要可以在model加上jsonignore，先留着*/
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsCreationsDTOList));
    }

    @ApiOperation(" 4.3审核创建社团申请 ")
    @PutMapping("/creations/audit")
    public ResponseEntity<Object> clubCreateAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubCreationsAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.4提交解散社团申请表单 ")
    @PostMapping("/dissolution")
    public ResponseEntity<Object> clubDissolve(@Validated @RequestBody CmsClubsDisbandParam cmsClubsDisbandParam){
        clubService.clubDisband(cmsClubsDisbandParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 4.5社团解散申请列表 ")
    @GetMapping("/dissolution")
    public ResponseEntity<Object> clubDisbandList(
        CmsClubsDisbandQueryParam cmsClubsDisbandQueryParam,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "limit", defaultValue = "3") Integer limit,
        @RequestParam(value = "sort", defaultValue = "id") String sort,
        @RequestParam(value = "order", defaultValue = "asc") String order,
        @RequestParam(value = "keyword", required = false) String keyword){
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        /*有的参数可能不需要返回，如果后面真的不需要可以在model加上jsonignore，先留着*/
        List<CmsClubsDisbandDTO> cmsClubsDisbandDTOList = clubService.listClubDisbandApply(cmsClubsDisbandQueryParam,queryParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsDisbandDTOList));
    }

    @ApiOperation(" 4.6审核解散社团申请 ")
    @PutMapping("/dissolution/audit")
    public ResponseEntity<Object> clubDisbandAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubDissolutionAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.7提交加入社团申请表单 ")
    @PostMapping("/join")
    public ResponseEntity<Object> clubJoin(@Validated @RequestBody CmsClubsJoinParam cmsClubsJoinParam){
        clubService.clubJoin(cmsClubsJoinParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = " 4.8根据社团 ID 获取申请列表 ")
    @GetMapping("/{clubId}/joins")
    public ResponseEntity<Object> joinsList(CmsClubsJoinQueryParam cmsClubsJoinQueryParam,
                                            @PathVariable("clubId") Integer clubId,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                            @RequestParam(value = "sort", defaultValue = "id") String sort,
                                            @RequestParam(value = "order", defaultValue = "asc") String order,
                                            @RequestParam(value = "keyword", required = false) String keyword) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        List<CmsClubsJoinDTO> cmsClubsJoinDTOList = clubService.listJoinClubApply(clubId, cmsClubsJoinQueryParam,queryParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsJoinDTOList));
    }

    @ApiOperation(" 4.9审核解散社团申请 ")
    @PutMapping("/joins/audit")
    public ResponseEntity<Object> clubJoinsAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubJoinsAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.10提交退出社团申请表单 ")
    @PostMapping("/quit")
    public ResponseEntity<Object> clubQuit(@Validated @RequestBody CmsClubsQuitParam cmsClubsQuitParam){
        clubService.clubQuit(cmsClubsQuitParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = " 4.11根据社团 ID 获取退社申请列表 ")
    @GetMapping("/{clubId}/quit")
    public ResponseEntity<Object> quitList(CmsClubsQuitQueryParam cmsClubsQuitQueryParam,
                                           @PathVariable("clubId") Integer clubId,
                                           @RequestParam(value = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                           @RequestParam(value = "sort", defaultValue = "id") String sort,
                                           @RequestParam(value = "order", defaultValue = "asc") String order,
                                           @RequestParam(value = "keyword", required = false) String keyword) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        List<CmsClubsQuitDTO> cmsClubsQuitDTOList = clubService.listClubQuit(clubId, cmsClubsQuitQueryParam,queryParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsQuitDTOList));
    }

    @ApiOperation(" 4.12提交社团换届申请表单 ")
    @PostMapping("/leader/change")
    public ResponseEntity<Object> clubChiefChange(@Validated @RequestBody CmsClubsChiefChangeParam cmsClubsChiefChangeParam){
        clubService.clubChiefChange(cmsClubsChiefChangeParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(" 4.13社团换届申请列表 ")
    @GetMapping("/leader/changes")
    public ResponseEntity<Object> clubChiefChangeList(
        CmsClubsChiefChangeQueryParam cmsClubsChiefChangeQueryParam,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                      @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                      @RequestParam(value = "order", defaultValue = "asc") String order,
                                                      @RequestParam(value = "keyword", required = false) String keyword){
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        List<CmsClubsChiefChangeDTO> cmsClubsChiefChangeDTOList = clubService.listClubChiefChangeApply(cmsClubsChiefChangeQueryParam,queryParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsChiefChangeDTOList));
    }

    @ApiOperation(" 4.14审核社团换届申请 ")
    @PutMapping("/leader/changes")
    public ResponseEntity<Object> clubChiefChangeAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubChiefChangeAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.15提交社团认证申请表单 ")
    @PostMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChange(@Validated @RequestBody CmsClubsCertificationsParam cmsClubsCertificationsParam){
        clubService.clubOfficialChange(cmsClubsCertificationsParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(" 4.16社团认证申请列表 ")
    @GetMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChangeList(
        CmsClubsCertificationsQueryParam cmsClubsCertificationsQueryParam,
                                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                         @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                         @RequestParam(value = "order", defaultValue = "asc") String order,
                                                         @RequestParam(value = "keyword", required = false) String keyword){
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        List<CmsClubsCertificationsDTO> cmsClubsCertificationsDTOList = clubService.listClubOfficialChange(cmsClubsCertificationsQueryParam,queryParam);
        return ResponseEntity.ok().body(CommonPage.restPage(cmsClubsCertificationsDTOList));
    }

    @ApiOperation(" 4.17审核社团认证申请 ")
    @PutMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChangeAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        // TODO: 等待api修改
        clubService.clubOfficialChangeAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }
}
