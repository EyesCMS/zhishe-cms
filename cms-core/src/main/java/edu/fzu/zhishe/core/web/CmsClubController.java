package edu.fzu.zhishe.core.web;


import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @ApiOperation(" 3.1推荐社团列表 ")
    @GetMapping("/recommended")
    // @PreAuthorize("hasAuthority('cms:club:read')")
    public ResponseEntity<CommonPage<CmsClubBriefDTO>> recommendedClub(
            @Validated PaginationParam paginationParam, OrderByParam orderByParam) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listHotClub(paginationParam, orderByParam)));
    }

    @ApiOperation(" 3.2 查看社团列表 ")
    @GetMapping("")
    public ResponseEntity<CommonPage<CmsClubBriefDTO>> searchClubByKeyword(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                                        @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                                        @RequestParam(value = "order", defaultValue = "asc") String order,
                                                                           @RequestParam(value = "keyword", required = false) String keyword,
                                                                           @RequestParam(value = "type", required = false) String type,
                                                                           @RequestParam(value = "state", required = false) Integer state){
        return ResponseEntity.ok(CommonPage.restPage(clubService.listClub(page, limit, sort, order, keyword, type, state)));
    }

    @ApiOperation(" 3.3查看学生加入/管理的社团列表 ")
    @GetMapping("/user/{userId}/clubs")
    public ResponseEntity<CommonPage<CmsClubBriefDTO>> joinedClubList(@Validated PaginationParam paginationParam, OrderByParam orderByParam,
                                                                    @PathVariable(value = "userId", required = false) Integer userId,
                                                                    @RequestParam(value = "status", required = false) String status){
        if("member".equals(status)){
            return ResponseEntity.ok(CommonPage.restPage(clubService.listJoinedClub(paginationParam, orderByParam, userId)));
        } else {
            return ResponseEntity.ok(CommonPage.restPage(clubService.listManagedClub(paginationParam, orderByParam, userId)));
        }
    }

    @ApiOperation(" 3.4查看某个社团详情 ")
    @GetMapping("/{id}")
    public ResponseEntity<CmsClubDetailDTO> searchClubById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @ApiOperation(" 3.5查看学生加入社团申请列表 ")
    @GetMapping("/join/{userId}")
    public ResponseEntity<CommonPage<CmsClubJoinApplyDTO>> joinedApplyList(@Validated PaginationParam paginationParam, OrderByParam orderByParam,
                                                                    @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listJoinClubApply(paginationParam, orderByParam, userId)));
    }

    @ApiOperation(" 3.6查看学生创建社团申请列表 ")
    @GetMapping("/creations/{userId}")
    public ResponseEntity<CommonPage<CmsClubCreateApplyDTO>> createClubApplyList(@Validated PaginationParam paginationParam, OrderByParam orderByParam,
                                                                        @PathVariable(value = "userId", required = false) Integer userId) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listCreateClubApply(paginationParam, orderByParam, userId)));
    }

    @ApiOperation(" 3.7查看社团成员列表 ")
    @GetMapping("/{clubId}/members")
    public ResponseEntity<CommonPage<CmsClubMemberBriefDTO>> listClubMember(@Validated PaginationParam paginationParam, OrderByParam orderByParam,
                                                                            @PathVariable(value = "clubId", required = false) Integer clubId) {
        return ResponseEntity.ok(CommonPage.restPage(clubService.listClubMember(paginationParam, orderByParam, clubId)));
    }

    @ApiOperation(" 3.8查看某个社员详情 ")
    @GetMapping("/{clubId}/members/{userId}")
    public ResponseEntity<CmsClubMemberDetailDTO> showClubMemberInfo(@PathVariable("clubId") Integer clubId,
                                                                    @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(clubService.showClubMemberInfo(clubId, userId));
    }

/*    见：CmsClubServiceImpl       public Integer addClubMember(Integer clubId, Integer userId)
    @ApiOperation(" 3.9添加社团成员 ")
    public ResponseEntity<Integer> addClubMember(@PathVariable("clubId") Integer clubId,
                                                 @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(clubService.addClubMember(clubId, userId));
    }
*/

    @ApiOperation(" 3.10删除社团成员 ")
    @DeleteMapping("/{clubId}/members/{userId}")
    public ResponseEntity<Integer> deleteClubMember(@PathVariable("clubId") Integer clubId,
                                                    @PathVariable("userId") Integer userId) {
        clubService.deleteClubMember(clubId, userId);
        return ResponseEntity.noContent().build();
    }

}
