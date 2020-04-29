package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.common.exception.EntityNotFoundException;
import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.common.util.PageUtil;
import edu.fzu.zhishe.core.dto.CmsActivityDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkDTO;
import edu.fzu.zhishe.core.dto.CmsRemarkParam;
import edu.fzu.zhishe.core.dto.QueryParam;
import edu.fzu.zhishe.core.service.CmsForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@RestController
@RequestMapping("/forum")
@Api(tags = "CmsForumController")
public class CmsForumController {

    @Autowired
    private CmsForumService forumService;

    @ApiOperation(" 7.1 帖子列表 ")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<CmsActivityDTO>> listPosts(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                            @RequestParam(value = "sort", defaultValue = "id") String sort,
                                            @RequestParam(value = "order", defaultValue = "asc") String order,
                                            @RequestParam(value = "keyword", required = false) String title) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, title);
        return ResponseEntity.ok().body(CommonPage.restPage(forumService.listPosts(null, queryParam)));
    }

    @ApiOperation(" 7.2 根据活动 id 查看某一帖子 ")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ResponseEntity<CmsActivityDTO> getPost(@PathVariable Integer id) {
        Optional<CmsActivityDTO> activityDTO = Optional.ofNullable(forumService.getActivityById(id));
        return ResponseEntity.ok().body(activityDTO.orElseThrow(() -> new EntityNotFoundException("id 为 " + id + " 的活动不存在")));
    }

    @ApiOperation(" 7.3 删除一条帖子(活动) ")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable Integer id) {
        if (forumService.deleteActivity(id) == 0) {
            Asserts.fail(" 操作失败 ");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 7.5 某个社团的帖子列表 ")
    @RequestMapping(value = "/{clubId}/posts", method = RequestMethod.GET)
    public ResponseEntity<CommonPage<CmsActivityDTO>> listClubPosts(@PathVariable("clubId") Integer clubId,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                @RequestParam(value = "order", defaultValue = "asc") String order,
                                                @RequestParam(value = "keyword", required = false) String title) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, title);
        List<CmsActivityDTO> activities = forumService.listPosts(clubId, queryParam);
        return ResponseEntity.ok().body(CommonPage.restPage(activities));
    }

    @ApiOperation(" 8.1 对某一活动帖子发表评论 ")
    @RequestMapping(value = "/posts/{id}/remarks", method = RequestMethod.POST)
    public ResponseEntity<Object> createRemark(@PathVariable("id") Integer postId,
                                               @RequestBody CmsRemarkParam remarkParam) {
        if (forumService.postRemark(remarkParam) == 0) {
            Asserts.fail(" 操作失败 ");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 8.2 获取某一活动的评论列表 ")
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
