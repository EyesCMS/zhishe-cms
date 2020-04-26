package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.common.util.CommonList;
import edu.fzu.zhishe.core.dto.CmsActivityDTO;
import edu.fzu.zhishe.core.dto.CmsActivityDetails;
import edu.fzu.zhishe.core.dto.QueryParam;
import edu.fzu.zhishe.core.service.CmsForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation(" 帖子列表 ")
    @GetMapping("/posts")
    public ResponseEntity<Object> listPosts(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                            @RequestParam(value = "sort", defaultValue = "id") String sort,
                                            @RequestParam(value = "order", defaultValue = "asc") String order,
                                            @RequestParam(value = "keyword", required = false) String title) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, title);
        List<CmsActivityDTO> activities = forumService.listPosts(null, queryParam);
        return ResponseEntity.ok().body(CommonList.getCommonList(activities, activities.size()));
    }

    @ApiOperation(" 某个社团的帖子列表 ")
    @GetMapping("/{clubId}/posts")
    public ResponseEntity<Object> listClubPosts(@PathVariable("clubId") Integer clubId,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "limit", defaultValue = "3") Integer limit,
                                                @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                @RequestParam(value = "order", defaultValue = "asc") String order,
                                                @RequestParam(value = "keyword", required = false) String title) {
        QueryParam queryParam = new QueryParam(page, limit, sort, order, title);
        List<CmsActivityDTO> activities = forumService.listPosts(clubId, queryParam);
        return ResponseEntity.ok().body(CommonList.getCommonList(activities, activities.size()));
    }

    @ApiOperation(" 帖子详情 ")
    @GetMapping("/posts/{id}")
    public ResponseEntity<CmsActivityDetails> getPostDetail(@PathVariable Integer id) {
        return ResponseEntity.ok().body(forumService.getActivityDetailById(id));
    }
}
