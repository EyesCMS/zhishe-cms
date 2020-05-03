package edu.fzu.zhishe.core.web;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.CmsActivitySearchParam;
import edu.fzu.zhishe.core.dto.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.dto.CmsAtivityApplyListDTO;
import edu.fzu.zhishe.core.dto.CmsClubActivityParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PSF
 * @version 1.0
 */
@RestController
@RequestMapping("/clubs")
public class CmsActivityController {

    @Autowired
    private CmsClubService clubService;

    @ApiOperation(" 6.1 申请活动 ")
    @PostMapping("/activities")
    public ResponseEntity<Object> activityApply(@Validated @RequestBody CmsClubActivityParam param){
        clubService.activityApply(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 6.2 管理员获取社团活动申请 ")
    @GetMapping("/activities")
    public ResponseEntity<Object> listActivitiesApply(@RequestBody CmsActivitySearchParam param,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "asc") String order,
            @RequestParam(value = "keyword", required = false) String keyword){
        List<CmsAtivityApplyListDTO> activities = clubService.listActivitiesApply(param, page, limit, sort, order);
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", activities.size());
        result.put("items", activities);
        return ResponseEntity.ok().body(result);
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

    @ApiOperation(" 6.5 社长修改社团活动 ")
    @PutMapping("/activities/{id}")
    public ResponseEntity<Object> updateActivity(@PathVariable(value = "id") Integer id,
                                                 @Validated @RequestBody CmsActivityUpdateParam param) {
        clubService.updateActivity(id, param);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 6.6 根据活动id删除活动 ")
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Object> delActivity(@PathVariable(value = "id") Integer id) {
        clubService.delActivity(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 6.7 社长可以获取自己社团申请的活动列表 ")
    @GetMapping("/{clubId}/activities/apply")
    public ResponseEntity<Object> getApply(@PathVariable(value = "clubId") Integer clubId) {
        return ResponseEntity.ok().body(clubService.getActivitiesApply(clubId));
    }

    @ApiOperation(" 6.8 社长可以获取自己社团申请的某一活动的详情 ")
    @GetMapping("/activities/apply/{id}")
    public ResponseEntity<Object> getApplyItem(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().body(clubService.getActivityApplyItem(id));
    }
}
