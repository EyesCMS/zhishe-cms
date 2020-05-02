package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.dto.FmsPostParam;
import edu.fzu.zhishe.core.dto.FmsRemarkParam;
import edu.fzu.zhishe.core.dto.QueryParam;
import edu.fzu.zhishe.core.service.FmsForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(" 7.1 帖子列表(个人/活动) ")
    //@PreAuthorize("hasAuthority('fms:post:read')")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<FmsPostDTO>> listPosts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "type") Integer type,
            @RequestParam(value = "keyword", required = false) String title) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, title);
        List<FmsPostDTO> postList = null;
        if (type == 0) {
             postList = forumService.listPersonalPost(null, queryParam);
        } else if (type == 1) {
            postList = forumService.listActivityPost(null, queryParam);
        } else {
            Asserts.fail("parameter 'originState' can only be assigned with 0 or 1");
        }
        return ResponseEntity.ok().body(CommonPage.restPage(postList));
    }

    @ApiOperation(" 7.2 查看某一帖子 ")
    //@PreAuthorize("hasAuthority('fms:remark:read')")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ResponseEntity<FmsPostDTO> getPost(
            @PathVariable("id") Integer id, @RequestParam(value = "type") Integer type) {
        Optional<FmsPostDTO> postDTO;
        // 需要通过 type 来区分发帖人（用户名和头像）
        if (type == 0) {
            postDTO = Optional.ofNullable(forumService.getPersonalPostById(id));
        } else if (type == 1) {
            postDTO = Optional.ofNullable(forumService.getActivityPostById(id));
        } else {
            postDTO = Optional.empty();
            Asserts.fail("type must be 0 or 1");
        }

        return ResponseEntity.ok().body(postDTO.orElseThrow(() -> new EntityNotFoundException("Post doesn't exist")));
    }

    @ApiOperation(" 7.3 删除一条帖子 ")
    @PreAuthorize("hasAuthority('fms:remark:delete')")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
        if (forumService.deletePost(id) == 0) {
            Asserts.fail();
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 7.4 发布个人帖 ")
    @PreAuthorize("hasAuthority('fms:remark:create')")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity<Object> savePost(@RequestBody FmsPostParam postParam) {
        if (forumService.savePost(postParam) == 0) {
            Asserts.fail();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 7.5 修改个人帖 ")
    @PreAuthorize("hasAuthority('fms:post:update')")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePost(@PathVariable("id") Long id, @RequestBody FmsPostParam postParam) {
        // TODO: 校验身份
        if (forumService.updatePost(id, postParam) == 0) {
            Asserts.fail();
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 7.6 某个社团的活动帖子列表 ")
    //@PreAuthorize("hasAuthority('fms:post:read')")
    @RequestMapping(value = "/{clubId}/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<FmsPostDTO>> listClubPosts(@PathVariable("clubId") Integer clubId,
                                                @RequestParam(value = "type") Integer type,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                @RequestParam(value = "order", defaultValue = "asc") String order) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, null);
        List<FmsPostDTO> postList = null;
        if (type == 0) {
            postList = forumService.listPersonalPost(clubId, queryParam);
        } else if (type == 1) {
            postList = forumService.listActivityPost(clubId, queryParam);
        } else {
            Asserts.fail("parameter 'originState' can only be assigned with 0 or 1");
        }
        return ResponseEntity.ok().body(CommonPage.restPage(postList));
    }

    // 登陆后才可
    @ApiOperation(" 8.1 对某一帖子发表评论 ")
    @PreAuthorize("hasAuthority('fms:remark:create')")
    @RequestMapping(value = "/posts/remarks", method = RequestMethod.POST)
    public ResponseEntity<Object> createRemark(@RequestBody FmsRemarkParam remarkParam) {
        if (forumService.saveRemark(remarkParam) == 0) {
            Asserts.fail();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 8.2 获取某一帖子的评论列表 ")
    //@PreAuthorize("hasAuthority('fms:remark:read')")
    @RequestMapping(value = "/posts/{id}/remarks", method = RequestMethod.GET)
    public ResponseEntity<Object> getRemarksByPostId(@PathVariable("id") Long postId,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "limit", defaultValue = "3") Integer limit) {
        return ResponseEntity.ok().body(CommonPage.restPage(forumService.listRemarkByPostId(postId, page, limit)));
    }
}