package edu.fzu.zhishe.core.web;


import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApply;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.dto.CmsClubsAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandParam;
import edu.fzu.zhishe.core.dto.CmsClubsJoinParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *社团管理
 *
 * @author yang
 */
@RestController
@Api(tags = "CmsClubController")
@RequestMapping("/clubs")
public class CmsClubController {

    private final CmsClubService clubService;

    public CmsClubController(CmsClubService clubService) {
        this.clubService = clubService;
    }

    @ApiOperation(" 3.1推荐社团列表 ")
    @GetMapping("/recommended")
    @PreAuthorize("hasAuthority('cms:club:read')")
    public ResponseEntity<List<CmsClub>> recommendedClub(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return ResponseEntity.ok(clubService.hotClubList(page, limit));
    }

    @ApiOperation(" 3.2查看社团列表 ")
    @GetMapping("")
    public ResponseEntity<List<CmsClub>> showClub(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "limit", defaultValue = "3") Integer limit) {
        return ResponseEntity.ok(clubService.getClubList(page, limit));
    }

    @ApiOperation(" 4.1提交创建社团申请表单 ")
    @PostMapping("/creations")
    public ResponseEntity<Object> clubCreate(@Validated @RequestBody CmsClubsCreationsParam cmsClubsCreationsParam){
        clubService.clubCreate(cmsClubsCreationsParam);
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
    public ResponseEntity<Object> clubCreateList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                 @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                 @RequestParam(value = "order", defaultValue = "asc") String order,
                                                 @RequestParam(value = "keyword") String keyword){
        CommonList clubCreateApplyList = clubService.getClubCreateList(page,limit,sort,order);
        /*id 和 userid可能不需要，如果后面真的不需要可以在model加上jsonignore，先留着*/
        return ResponseEntity.ok().body(clubCreateApplyList);
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
    public ResponseEntity<Object> clubDisbandList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                  @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                  @RequestParam(value = "order", defaultValue = "asc") String order,
                                                  @RequestParam(value = "keyword") String keyword){
        CommonList clubDisbandApplyList = clubService.getClubDisbandList(page,limit,sort,order);
        /*有的参数可能不需要返回，如果后面真的不需要可以在model加上jsonignore，先留着*/
        return ResponseEntity.ok().body(clubDisbandApplyList);
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
    public ResponseEntity<Object> joinsList(@PathVariable("clubId") Integer clubId,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                            @RequestParam(value = "sort", defaultValue = "id") String sort,
                                            @RequestParam(value = "order", defaultValue = "asc") String order,
                                            @RequestParam(value = "keyword") String keyword) {
        return ResponseEntity.ok().body(clubService.getClubJoinsList(clubId,page,limit,sort,order));
    }

    @ApiOperation(" 4.9审核解散社团申请 ")
    @PutMapping("/joins/audit")
    public ResponseEntity<Object> clubJoinsAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubJoinsAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }
}
