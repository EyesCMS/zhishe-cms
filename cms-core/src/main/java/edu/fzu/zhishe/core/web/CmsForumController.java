package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.dto.CmsPostDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkParam;
import edu.fzu.zhishe.core.dto.QueryParam;
import edu.fzu.zhishe.core.service.CmsForumService;
import edu.fzu.zhishe.core.validator.FlagValidator;
import io.swagger.annotations.Api;
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
@Api(tags = "CmsForumController")
public class CmsForumController {

    @Autowired
    private CmsForumService forumService;

    @ApiOperation(" 7.1 帖子列表(个人/活动) ")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<CmsPostDTO>> listPosts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "originState") Integer originState,
            @RequestParam(value = "keyword", required = false) String title) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, title);
        List<CmsPostDTO> postList = null;
        if (originState == 0) {
            // TODO 个人贴
             postList = forumService.listActivityPost(null, queryParam);
        } else if (originState == 1) {
            // 活动帖
            postList = forumService.listActivityPost(null, queryParam);
            // postList = forumService.listPersonalPost(queryParam);
        } else {
            Asserts.fail("parameter 'originState' can only be assigned with 0 or 1");
        }
        return ResponseEntity.ok().body(CommonPage.restPage(postList));
    }

    @ApiOperation(" 7.2 查看某一帖子 ")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ResponseEntity<CmsPostDTO> getPost(
            @PathVariable("id") Integer id, @RequestParam(value = "originState") Integer originState) {
        Optional<CmsPostDTO> postDTO;
        if (originState == 0) {
            // TODO: personal post
            postDTO = Optional.ofNullable(forumService.getActivityPostById(id));
        } else {
            postDTO = Optional.ofNullable(forumService.getActivityPostById(id));
        }

        return ResponseEntity.ok().body(postDTO.orElseThrow(() -> new EntityNotFoundException("Post doesn't exist")));
    }

    @ApiOperation(" 7.3 删除一条个人帖子 ")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable Integer id) {
        // TODO
        if (false) {
            Asserts.fail(" 操作失败 ");
        }
        return ResponseEntity.noContent().build();
    }

    // TODO
//    @ApiOperation(" 7.4 发布个人帖 ")
    // TODO
//    @ApiOperation(" 7.5 修改个人帖 ")

    @ApiOperation(" 7.6 某个社团的活动帖子列表 ")
    @RequestMapping(value = "/{clubId}/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<CmsPostDTO>> listClubPosts(@PathVariable("clubId") Integer clubId,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                @RequestParam(value = "order", defaultValue = "asc") String order) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, null);
        return ResponseEntity.ok().body(CommonPage.restPage(forumService.listActivityPost(clubId, queryParam)));
    }

    @ApiOperation(" 8.1 对某一帖子发表评论 ")
    @RequestMapping(value = "/posts/{id}/remarks", method = RequestMethod.POST)
    public ResponseEntity<Object> createRemark(@PathVariable("id") Integer postId,
                                               @RequestBody CmsRemarkParam remarkParam) {
        if (forumService.saveRemark(remarkParam) == 0) {
            Asserts.fail(" 操作失败 ");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 8.2 获取某一帖子的评论列表 ")
    @RequestMapping(value = "/posts/{actId}/remarks", method = RequestMethod.GET)
    public ResponseEntity<Object> getRemarksByPostId(@PathVariable("actId") Integer postId,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                     @RequestParam(value = "sort", defaultValue = "create_at") String sort,
                                                     @RequestParam(value = "order", defaultValue = "asc") String order) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, "");
        List<CmsRemarkDTO> remarkList = forumService.listRemarkByPostId(postId, queryParam);
        CommonList commonList = CommonList.getCommonList(remarkList, (int) forumService.countRemarkByPostId(postId));
        return ResponseEntity.ok().body(commonList);
    }
}
