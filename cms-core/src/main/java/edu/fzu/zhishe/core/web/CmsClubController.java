package edu.fzu.zhishe.core.web;


import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.*;
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

    //暂未完善
    @ApiOperation(" 3.1推荐社团列表 ")
    @GetMapping("/recommended")
    // @PreAuthorize("hasAuthority('cms:club:read')")
    public ResponseEntity<List<CmsClub>> recommendedClub(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        return ResponseEntity.ok(clubService.getHotClubList(page, limit));
    }

    @ApiOperation(" 3.2查看社团列表 ")
    @GetMapping("")
    public ResponseEntity<List<CmsClub>> showClub(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                  @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                  @RequestParam(value = "order", defaultValue = "asc") String order) {
        return ResponseEntity.ok(clubService.getClubList(page, limit, sort, order));
    }

    @ApiOperation(" 3.3按名称关键字查找社团 ")
    @GetMapping("/")
    public ResponseEntity<List<CmsClub>> searchClubByKeyword(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                    @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                    @RequestParam(value = "order", defaultValue = "asc") String order,
                                                    @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseEntity.ok(clubService.searchClubByKeyword(page, limit, sort, order, keyword));
    }

    @ApiOperation(" 3.4查看某个社团详情 ")
    @GetMapping("/{id}")
    public ResponseEntity<CmsClub> searchClubById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(clubService.searchClubById(id).get(0));
    }

    @ApiOperation(" 3.2查看学生加入的社团列表 ")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CmsClub>> searchJoinedClubList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                             @RequestParam(value = "order", defaultValue = "asc") String order,
                                                             @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(clubService.searchJoinedClub(page, limit, sort, order, userId));
    }

    @ApiOperation(" 3.2查看学生管理的社团列表 ")
    @GetMapping("/manager/{userId}")
    public ResponseEntity<List<CmsClub>> searchManagedClubList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                              @RequestParam(value = "order", defaultValue = "asc") String order,
                                                              @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(clubService.searchManagedClub(page, limit, sort, order, userId));
    }

    @ApiOperation(" 3.4查看学生加入社团申请列表 ")
    @GetMapping("/join/{userId}")
    public ResponseEntity<List<CmsClub>> searchJoinedApplyList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                              @RequestParam(value = "order", defaultValue = "asc") String order,
                                                              @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(clubService.searchJoinedApplyList(page, limit, sort, order, userId));
    }

    @ApiOperation(" 3.4查看学生创建社团申请列表 ")
    @GetMapping("/creations/{userId}")
    public ResponseEntity<List<CmsClub>> searchCreateApplyList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                               @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                               @RequestParam(value = "order", defaultValue = "asc") String order,
                                                               @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(clubService.searchCreateApplyList(page, limit, sort, order, userId));
    }

    @ApiOperation(" 4.1提交创建社团申请表单 ")
    @PostMapping("/creations")
    public ResponseEntity<Object> clubCreate(@Validated @RequestBody CmsClubsCreationsParam cmsClubsCreationsParam){
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
    public ResponseEntity<CommonPage<CmsClubCreateApply>> clubCreateList(CmsClubCreationQueryParam queryParam,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "limit", defaultValue = "3") Integer limit) {
        List<CmsClubCreateApply> clubCreateApplyList = clubService.listClubCreationApply(queryParam, page, limit);
        /*id 和 userid可能不需要，如果后面真的不需要可以在model加上jsonignore，先留着*/
        return ResponseEntity.ok().body(CommonPage.restPage(clubCreateApplyList));
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
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        CommonList clubDisbandApplyList = clubService.getClubDisbandList(queryParam);
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
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        return ResponseEntity.ok().body(clubService.getClubJoinsList(clubId,queryParam));
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
    public ResponseEntity<Object> quitList(@PathVariable("clubId") Integer clubId,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                            @RequestParam(value = "sort", defaultValue = "id") String sort,
                                            @RequestParam(value = "order", defaultValue = "asc") String order,
                                            @RequestParam(value = "keyword") String keyword) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        return ResponseEntity.ok().body(clubService.getClubQuitList(clubId,queryParam));
    }

    @ApiOperation(" 4.12提交社团换届申请表单 ")
    @PostMapping("/leader/change")
    public ResponseEntity<Object> clubChiefChange(@Validated @RequestBody CmsClubsChiefChangeParam cmsClubsChiefChangeParam){
        clubService.clubChiefChange(cmsClubsChiefChangeParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(" 4.13社团换届申请列表 ")
    @GetMapping("/leader/changes")
    public ResponseEntity<Object> clubChiefChangeList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                 @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                 @RequestParam(value = "order", defaultValue = "asc") String order,
                                                 @RequestParam(value = "keyword") String keyword){
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        CommonList clubChiefChangeList = clubService.getClubChiefChangeList(queryParam);
        return ResponseEntity.ok().body(clubChiefChangeList);
    }

    @ApiOperation(" 4.14审核社团换届申请 ")
    @PutMapping("/leader/changes")
    public ResponseEntity<Object> clubChiefChangeAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        clubService.clubChiefChangeAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 4.15提交社团认证申请表单 ")
    @PostMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChange(@Validated @RequestBody CmsCertificationsParam cmsCertificationsParam){
        clubService.clubOfficialChange(cmsCertificationsParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     *
     * 以下两个控制器是复制的暂未实现
     *
     */
    @ApiOperation(" 4.16社团认证申请列表 ")
    @GetMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChangeList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                      @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                      @RequestParam(value = "order", defaultValue = "asc") String order,
                                                      @RequestParam(value = "keyword") String keyword){
        QueryParam queryParam = new QueryParam(page, limit, sort, order, keyword);
        // TODO: 等待api修改
       // CommonList clubChiefChangeList = clubService.getClubChiefChangeList(queryParam);
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(" 4.17审核社团认证申请 ")
    @PutMapping("/certifications")
    public ResponseEntity<Object> clubOfficialChangeAudit(@Validated @RequestBody CmsClubsAuditParam cmsClubsAuditParam){
        // TODO: 等待api修改
        //clubService.clubChiefChangeAudit(cmsClubsAuditParam);
        return ResponseEntity.noContent().build();
    }

    /*
     * @author PSF
     */

    @ApiOperation(" 6.1 申请活动 ")
    @PostMapping("/activities")
    public ResponseEntity<Object> activityApply(@Validated @RequestBody CmsClubActivityParam param){
        clubService.ativityApply(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 6.3 活动申请审核 ")
    @PutMapping("/activities/audit")
    public ResponseEntity<Object> activityAudit(@RequestBody JSONObject object){
        clubService.activityStateChange((Integer) object.get("id"),
            (Integer) object.get("state_id"), UserRoleEnum.ADMIN);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 6.4 修改社团活动状态 ")
    @PutMapping("/activities/state")
    public ResponseEntity<Object> activityStateChange(@RequestBody JSONObject object){
        clubService.activityStateChange((Integer) object.get("id"),
            (Integer) object.get("state_id"), UserRoleEnum.CHIEF);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 6.6 根据活动id删除活动 ")
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Object> delActivity(@PathVariable(value = "id") Integer id) {
        clubService.delActivity(id);
        return ResponseEntity.noContent().build();
    }
}
