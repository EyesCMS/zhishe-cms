package edu.fzu.zhishe.core.web;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.annotation.IsLogin;
import edu.fzu.zhishe.core.constant.LikedStatusEnum;
import edu.fzu.zhishe.core.constant.PostTypeEnum;
import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.error.DatabaseErrorEnum;
import edu.fzu.zhishe.core.error.PostErrorEnum;
import edu.fzu.zhishe.core.param.FmsPostParam;
import edu.fzu.zhishe.core.param.FmsPostQuery;
import edu.fzu.zhishe.core.param.FmsRemarkParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CreditService;
import edu.fzu.zhishe.core.service.FmsForumService;
import edu.fzu.zhishe.core.service.FmsLikeCacheService;
import edu.fzu.zhishe.core.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang on 4/30/2020.
 * @version 1.0
 */
@RestController
@RequestMapping("/forum")
@Api(tags = "FmsForumController")
public class FmsForumController {

    @Autowired
    private FmsForumService forumService;

    @Autowired
    private SysUserService userService;

    @Autowired
    FmsLikeCacheService likeCacheService;

    @Autowired
    CreditService creditService;

    @ApiOperation(" 7.1 帖子列表(个人/活动) ")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<FmsPostDTO>> listPosts(
        @Validated PaginationParam paginationParam,
        @RequestParam(value = "type") Integer type,
        FmsPostQuery postQuery) {

        List<FmsPostDTO> postList = null;
        if (type.equals(PostTypeEnum.PERSONAL.getValue())) {
             postList = forumService.listPersonalPost(null, paginationParam, postQuery);
        } else if (type.equals(PostTypeEnum.ACTIVITY.getValue())) {
            postList = forumService.listActivityPost(null, paginationParam, postQuery);
        } else {
            Asserts.fail("parameter 'originState' can only be assigned with 0 or 1");
        }
        return ResponseEntity.ok().body(CommonPage.restPage(postList));
    }

    @ApiOperation(" 7.2 查看某一帖子 ")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ResponseEntity<FmsPostDTO> getPost(
        @PathVariable("id") Integer id,
        @RequestParam(value = "type") Integer type) {

        Optional<FmsPostDTO> postDTO;
        // 需要通过 type 来区分发帖人（用户名和头像）
        if (type.equals(PostTypeEnum.PERSONAL.getValue())) {
            postDTO = Optional.ofNullable(forumService.getPersonalPostById(id));
        } else if (type.equals(PostTypeEnum.ACTIVITY.getValue())) {
            postDTO = Optional.ofNullable(forumService.getActivityPostById(id));
        } else {
            postDTO = Optional.empty();
            Asserts.fail("type must be 0 or 1");
        }

        return ResponseEntity.ok().body(postDTO.orElseThrow(() -> new EntityNotFoundException(
            PostErrorEnum.POST_NOT_EXIST.getMessage())));
    }

    @ApiOperation(" 7.3 删除一条帖子 ")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {

        if (forumService.deletePost(id) == 0) {
            Asserts.fail(DatabaseErrorEnum.DELETE_ERROR);
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 7.4 发布个人帖 ")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity<Object> savePost(@Validated FmsPostParam postParam) {

        if (forumService.savePost(postParam) == 0) {
            Asserts.fail(DatabaseErrorEnum.CREATE_ERROR);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 7.5 修改个人帖 ")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePost(@PathVariable("id") Long id, @Validated @RequestBody FmsPostParam postParam) {

        if (forumService.updatePost(id, postParam) == 0) {
            Asserts.fail(DatabaseErrorEnum.UPDATE_ERROR);
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 7.6 某个社团的帖子列表 ")
    @RequestMapping(value = "/{clubId}/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<FmsPostDTO>> listClubPosts(
        @PathVariable("clubId") Integer clubId,
        FmsPostQuery postQuery,
        @RequestParam(value = "type") Integer type,
        @Validated PaginationParam paginationParam) {

        List<FmsPostDTO> postList = null;
        if (type == 0) {
            postList = forumService.listPersonalPost(clubId, paginationParam, postQuery);
        } else if (type == 1) {
            postList = forumService.listActivityPost(clubId, paginationParam, postQuery);
        } else {
            Asserts.fail("parameter 'originState' can only be assigned with 0 or 1");
        }
        return ResponseEntity.ok().body(CommonPage.restPage(postList));
    }

    @ApiOperation(" 7.7 我的帖子列表 ")
    @RequestMapping(value = "/posts/mine", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<FmsPostDTO>> listMyPosts(
        @Validated PaginationParam paginationParam,
        FmsPostQuery postQuery) {

        return ResponseEntity.ok().body(CommonPage.restPage(forumService.listMyPost(paginationParam, postQuery)));
    }

    @IsLogin
    @ApiOperation(value = " 7.8 点赞 ")
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "likedPostId", value = " 被点赞的帖子 id"),
    })
    public ResponseEntity<Object> like(@RequestParam("likedPostId") Long pid) {

        likeCacheService.like(userService.getCurrentUser().getId(), pid);
        return noContent().build();
    }

    @IsLogin
    @ApiOperation(value = " 7.9 取消点赞 ")
    @RequestMapping(value = "/unlike", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "likedPostId", value = " 被取消点赞的帖子 id"),
    })
    public ResponseEntity<Object> unlike(@RequestParam("likedPostId") Long pid) {

        likeCacheService.unlike(userService.getCurrentUser().getId(), pid);
        return noContent().build();
    }

    @ApiOperation(value = " 7.10 查看当前用户对某一帖子的点赞情况 ")
    @RequestMapping(value = "/like", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "postId", value = " 帖子 id"),
    })
    public ResponseEntity<Object> getLikeStatus(@RequestParam("postId") Long postId) {

        boolean hasLiked = likeCacheService.hasLiked(userService.getCurrentUser().getId(), postId);
        int status = hasLiked ? LikedStatusEnum.LIKE.getCode() : LikedStatusEnum.UNLIKE.getCode();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        return ok().body(jsonObject);
    }

    @ApiOperation(" 8.1 对某一帖子发表评论 ")
    @RequestMapping(value = "/posts/remarks", method = RequestMethod.POST)
    public ResponseEntity<Object> createRemark(@Validated @RequestBody FmsRemarkParam remarkParam) {

        if (forumService.saveRemark(remarkParam) == 0) {
            Asserts.fail(DatabaseErrorEnum.CREATE_ERROR);
        }
        creditService.getCreditByComment(remarkParam.getPostId().intValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 8.2 获取某一帖子的评论列表 ")
    @RequestMapping(value = "/posts/{id}/remarks", method = RequestMethod.GET)
    public ResponseEntity<Object> getRemarksByPostId(
        @PathVariable("id") Long postId,
        @Validated PaginationParam paginationParam) {

        return ResponseEntity.ok().body(CommonPage.restPage(forumService.listRemarkByPostId(postId, paginationParam)));
    }

    @ApiOperation(" 8.3 删除某一评论 ")
    @RequestMapping(value = "/posts/remarks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> createRemark(@PathVariable("id") Long id) {

        if (forumService.deleteRemark(id) == 0) {
            Asserts.fail(DatabaseErrorEnum.DELETE_ERROR);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
