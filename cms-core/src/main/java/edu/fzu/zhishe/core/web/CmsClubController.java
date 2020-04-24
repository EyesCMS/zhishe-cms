package edu.fzu.zhishe.core.web;


import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.cms.model.CmsClubDisbandApply;
import edu.fzu.zhishe.core.dto.CmsClubsAuditParam;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.dto.CmsClubsDisbandParam;
import edu.fzu.zhishe.core.dto.CmsClubsJoinParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ApiOperation(" 推荐社团列表 ")
    @GetMapping("/recommended")
    public ResponseEntity<List<CmsClub>> recommendedClub(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return ResponseEntity.ok(clubService.hotClubList(page, limit));
    }

    @ApiOperation(" 4.1提交创建社团申请表单 ")
    @PostMapping("/creations")
    public ResponseEntity<Object> clubCreate(@RequestBody CmsClubsCreationsParam cmsClubsCreationsParam){
        clubService.clubCreate(cmsClubsCreationsParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 4.2社团创建申请列表 ")
    @GetMapping("/creations")
    public ResponseEntity<Object> clubCreateList(){
        List<CmsClubCreateApply> clubCreateApplyList = clubService.getClubCreateList();
        /*id 和 userid可能不需要，如果后面真的不需要可以在model加上jsonignore，先留着*/
        return ResponseEntity.ok().body(clubCreateApplyList);
    }

    @ApiOperation(" 4.3审核创建社团申请 ")
    @PutMapping("/creations/audit")
    public ResponseEntity<Object> clubCreateAudit(@RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubCreationsAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.4提交解散社团申请表单 ")
    @PostMapping("/dissolution")
    public ResponseEntity<Object> clubDissolve(@RequestBody CmsClubsDisbandParam cmsClubsDisbandParam){
        clubService.clubDisband(cmsClubsDisbandParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 4.5社团解散申请列表 ")
    @GetMapping("/dissolution")
    public ResponseEntity<Object> clubDisbandList(){
        List<CmsClubDisbandApply> clubDisbandApplyList = clubService.getClubDisbandList();
        /*有的参数可能不需要返回，如果后面真的不需要可以在model加上jsonignore，先留着*/
        return ResponseEntity.ok().body(clubDisbandApplyList);
    }

    @ApiOperation(" 4.6审核解散社团申请 ")
    @PutMapping("/dissolution/audit")
    public ResponseEntity<Object> clubDisbandAudit(@RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubDissolutionAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.7提交加入社团申请表单 ")
    @PostMapping("/join")
    public ResponseEntity<Object> clubJoin(@RequestBody CmsClubsJoinParam cmsClubsJoinParam){
        clubService.clubJoin(cmsClubsJoinParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
