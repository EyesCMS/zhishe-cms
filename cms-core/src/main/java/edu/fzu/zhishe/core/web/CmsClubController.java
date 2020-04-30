package edu.fzu.zhishe.core.web;


import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.core.service.CmsClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *社团管理
 *
 * @author
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
        return ResponseEntity.ok(clubService.listHotClub(page, limit));
    }

    @ApiOperation(" 3.2 3.3 查看社团列表 ")
    @GetMapping("")
    public ResponseEntity<CommonPage<CmsClub>> searchClubByKeyword(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                    @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                    @RequestParam(value = "order", defaultValue = "asc") String order,
                                                    @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listClub(page, limit, sort, order, keyword)));
    }

    @ApiOperation(" 3.4查看某个社团详情 ")
    @GetMapping("/{id}")
    public ResponseEntity<CmsClub> searchClubById(@PathVariable("id") String cid) {
        Integer id = Integer.parseInt(cid);
        return ResponseEntity.ok(clubService.getClubById(id).get(0));
    }

    @ApiOperation(" 3.2查看学生加入的社团列表 ")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CmsClub>> joinedClubList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                             @RequestParam(value = "order", defaultValue = "asc") String order,
                                                             @PathVariable(value = "userId", required = false) String userId) {
        Integer id = Integer.parseInt(userId);
        return ResponseEntity.ok(clubService.listJoinedClub(page, limit, sort, order, id));
    }

    @ApiOperation(" 3.2查看学生管理的社团列表 ")
    @GetMapping("/manager/{userId}")
    public ResponseEntity<List<CmsClub>> managedClubList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                              @RequestParam(value = "order", defaultValue = "asc") String order,
                                                              @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(clubService.listManagedClub(page, limit, sort, order, userId));
    }

    @ApiOperation(" 3.4查看学生加入社团申请列表 ")
    @GetMapping("/join/{userId}")
    public ResponseEntity<CommonPage<CmsClub>> joinedApplyList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                              @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                              @RequestParam(value = "order", defaultValue = "asc") String order,
                                                              @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listJoinClubApply(page, limit, sort, order, userId)));
    }

    @ApiOperation(" 3.4查看学生创建社团申请列表 ")
    @GetMapping("/creations/{userId}")
    public ResponseEntity<CommonPage<CmsClub>> createClubApplyList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                               @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                               @RequestParam(value = "order", defaultValue = "asc") String order,
                                                               @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listCreateClubApply(page, limit, sort, order, userId)));
    }

    //SQL待完善
    @ApiOperation(" 3.2查看社团成员列表 ")
    @GetMapping("/{clubId}/members")
    public ResponseEntity<CommonPage<CmsClub>> listClubMember(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                                   @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                                   @RequestParam(value = "order", defaultValue = "asc") String order,
                                                                   @PathVariable(value = "clubId", required = false) Integer clubId) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listClubMember(page, limit, sort, order, clubId)));
    }

    //SQL待完善
    @ApiOperation(" 3.4查看某个社员详情 ")
    @GetMapping("/{clubId}/members/{userId}")
    public ResponseEntity<CmsClub> showClubMemberInfo(@PathVariable("clubId") Integer clubId,
                                                      @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(clubService.showClubMemberInfo(clubId, userId));
    }

    //没写完没写完没写完 只写了个壳
    @ApiOperation(" 3.4添加社团成员 ")
    public ResponseEntity<CmsClub> addClubMember(@PathVariable("clubId") Integer clubId,
                                                 @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(clubService.addClubMember(clubId, userId).get(0));
    }
}
