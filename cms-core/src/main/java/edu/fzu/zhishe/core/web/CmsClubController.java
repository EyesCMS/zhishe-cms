package edu.fzu.zhishe.core.web;
import static org.springframework.http.ResponseEntity.ok;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.CheckClubAuth;
import edu.fzu.zhishe.core.annotation.IsClubMember;
import edu.fzu.zhishe.core.config.StorageProperties;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.param.CmsClubInfoParam;
import edu.fzu.zhishe.core.param.CmsClubMemberQuery;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import edu.fzu.zhishe.core.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 社团管理
 * @author wjh674
 */
@RestController
@Api(tags = "CmsClubController")
@RequestMapping("/clubs")
public class CmsClubController {

    private final CmsClubService clubService;

    @Autowired
    private StorageService storageService;

    private final Path imageRootLocation;

    public CmsClubController(CmsClubService clubService, StorageProperties storageProperties) {
        this.clubService = clubService;
        this.imageRootLocation = Paths.get(storageProperties.getImageLocation());
    }

    @ApiOperation(" 3.1 推荐社团列表 ")
    @GetMapping("/recommended")
    public ResponseEntity<CommonPage<CmsClubBriefDTO>> recommendedClub(
        @Validated PaginationParam paginationParam,
        OrderByParam orderByParam) {

        return ok(CommonPage.restPage(clubService.listHotClub(paginationParam, orderByParam)));
    }

    @ApiOperation(" 3.2 查看社团列表 ")
    @GetMapping
    public ResponseEntity<CommonPage<CmsClubBriefDTO>> searchClubByKeyword(
        @Validated PaginationParam paginationParam,
        OrderByParam orderByParam,
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "type", required = false) String type,
        @RequestParam(value = "state", required = false) Integer state) {

        return ok(CommonPage.restPage(clubService.listClub(paginationParam, orderByParam, keyword, type, state)));
    }

    @ApiOperation(" 3.3 查看学生加入/管理的社团列表 ")
    @GetMapping("/users/{userId}/clubs")
    public ResponseEntity<CommonPage<CmsClubBriefDTO>> joinedClubList(
        @Validated PaginationParam paginationParam,
        OrderByParam orderByParam,
        @PathVariable(value = "userId") Integer userId,
        @RequestParam(value = "status") String status) {

        if (ClubStatueEnum.MEMBER.getMsg().equals(status)) {
            return ok(CommonPage.restPage(clubService.listJoinedClub(paginationParam, orderByParam, userId)));
        } else {
            return ok(CommonPage.restPage(clubService.listManagedClub(paginationParam, orderByParam, userId)));
        }
    }

    @ApiOperation(" 3.4 查看某个社团详情 ")
    @GetMapping("/{id}")
    public ResponseEntity<CmsClubDetailDTO> searchClubById(@PathVariable("id") Integer id) {

        return ok(clubService.getClubById(id));
    }

    /**
     * 需要登陆
     */
    @ApiOperation(" 3.5 学生查看自己的加入社团申请列表 ")
    @GetMapping("/users/joins")
    public ResponseEntity<CommonPage<CmsClubJoinApplyDTO>> joinedApplyList(
        @Validated PaginationParam paginationParam,
        OrderByParam orderByParam) {

        return ok(CommonPage.restPage(clubService.listJoinClubApply(paginationParam, orderByParam)));
    }

    /**
     * 需要登陆
     */
    @ApiOperation(" 3.6 学生查看自己的创建社团申请列表 ")
    @GetMapping("/users/creations")
    public ResponseEntity<CommonPage<CmsClubCreateApplyDTO>> createClubApplyList(
        @Validated PaginationParam paginationParam,
        OrderByParam orderByParam) {

        return ok(CommonPage.restPage(clubService.listCreateClubApply(paginationParam, orderByParam)));
    }

    @ApiOperation(" 3.7 社员或社长查看社团成员列表 ")
    @GetMapping("/{clubId}/members")
    @IsClubMember
    public ResponseEntity<CommonPage<CmsClubMemberBriefDTO>> listClubMember(
        @Validated PaginationParam paginationParam,
        @PathVariable(value = "clubId") Integer clubId,
        CmsClubMemberQuery clubMemberQuery) {

        return ok(CommonPage.restPage(clubService.listClubMember(paginationParam, clubId, clubMemberQuery)));
    }

    @ApiOperation(" 3.8 社员或社长查看某个社员详情 ")
    @GetMapping("/{clubId}/members/{userId}")
    @IsClubMember
    public ResponseEntity<CmsClubMemberDetailDTO> showClubMemberInfo(
        @PathVariable("clubId") Integer clubId,
        @PathVariable("userId") Integer userId) {

        return ok(clubService.getClubMemberInfo(clubId, userId));
    }

    @ApiOperation(" 3.10 社长删除社团成员 ")
    @DeleteMapping("/{clubId}/members/{userId}")
    @CheckClubAuth("3")
    public ResponseEntity<Integer> deleteClubMember(
        @PathVariable("clubId") Integer clubId,
        @PathVariable("userId") Integer userId) {

        clubService.deleteClubMember(clubId, userId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 3.11 社长修改社团信息 ")
    @RequestMapping(value = "/{clubId}/info", method = RequestMethod.PUT)
    @CheckClubAuth("3")
    public ResponseEntity<Object> updateClubInfo(
        @PathVariable("clubId") Integer clubId,
        @RequestBody CmsClubInfoParam clubInfoParam) {

        clubService.updateClubInfo(clubId, clubInfoParam);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 3.12 社长修改社团头像（url） ")
    @RequestMapping(value = "/{clubId}/pic", method = RequestMethod.PUT)
    public ResponseEntity<Object> alterClubAvatarUrl(
        @PathVariable("clubId") Integer clubId,
        @RequestBody JSONObject object) {

        clubService.updateClubAvatarUrl(clubId, (String)object.get("avatarUrl"));
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 3.13 社长修改社团头像(上传） ")
    @RequestMapping(value = "/{clubId}/info/avatar", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadAvatar(
        @PathVariable("clubId") Integer clubId,
        @RequestParam("image") MultipartFile image) {

        String url = storageService.store(image, imageRootLocation);
        if (clubService.updateClubAvatarUrl(clubId, url) == 0) {
            Asserts.fail("update avatar failed");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avatarUrl", url);
        return ok().body(jsonObject);
    }

    @ApiOperation(" 3.14 查看社团走马灯 ")
    @GetMapping("/{clubId}/pictures")
    public ResponseEntity<String[]> getClubPic(@PathVariable("clubId") Integer clubId) {

        return ok(clubService.getClubPicture(clubId));
    }

    @ApiOperation(" 3.15 社长修改社团走马灯(上传） ")
    @PostMapping("/{clubId}/pictures")
    public ResponseEntity<Object> uploadPicture(
        @PathVariable("clubId") Integer clubId,
        @RequestParam("image") MultipartFile[] image) {

        String[] url = {null,null,null,null,null};
        for(int i = 0; i < image.length; i ++ ) {
            if(image[i] != null && i < 5) {
                url[i] = storageService.store(image[i], imageRootLocation);
            }
        }
        if (clubService.updateClubPictureUrl(clubId, url) == 0) {
            Asserts.fail("update picture failed");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        return ok().body(jsonObject);
    }

}
