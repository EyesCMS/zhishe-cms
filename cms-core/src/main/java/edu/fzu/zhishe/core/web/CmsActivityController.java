package edu.fzu.zhishe.core.web;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.param.CmsActivityQuery;
import edu.fzu.zhishe.core.param.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsActivityService;
import edu.fzu.zhishe.core.service.CmsClubService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
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
    private CmsActivityService clubService;

    @ApiOperation(" 6.1 申请活动 ")
    @PostMapping("/activities")
    public ResponseEntity<Object> activityApply(@Validated @RequestBody CmsClubActivityParam param){
        clubService.activityApply(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 6.2 管理员获取社团活动申请 ")
    @GetMapping("/activities")
    public ResponseEntity<Object> listActivitiesApply(CmsActivityQuery param,
            @Validated PaginationParam paginationParam, OrderByParam orderByParam) {
        return ResponseEntity.ok().body(CommonPage.restPage(clubService.listActivitiesApply(param, paginationParam, orderByParam)));
    }

    @ApiOperation(" 6.3 活动申请审核 ")
    @PutMapping("/activities/audit")
    public ResponseEntity<Object> activityAudit(@RequestBody JSONObject object){
        if((Integer)object.get("id") == null || (Integer)object.get("state") == 0) {
            Asserts.fail("请输入id和state");
        }
        clubService.activityStateChange((Integer)object.get("id"),
            (Integer)object.get("state"), UserRoleEnum.ADMIN);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(" 6.4 修改社团活动状态 ")
    @PutMapping("/activities/state")
    public ResponseEntity<Object> activityStateChange(@RequestBody JSONObject object){
        if((Integer)object.get("id") == null || (Integer)object.get("state") == 0) {
            Asserts.fail("请输入id和state");
        }
        clubService.activityStateChange((Integer) object.get("id"),
            (Integer) object.get("state"), UserRoleEnum.CHIEF);
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
    public ResponseEntity<Object> getApply(@PathVariable(value = "clubId") Integer clubId,
                                           CmsActivityQuery param,
            @Validated PaginationParam paginationParam, OrderByParam orderByParam,
            @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseEntity.ok().body(CommonPage.restPage(clubService.listActivitiesApply(clubId, param,paginationParam, orderByParam)));
    }

    @ApiOperation(" 6.8 社长可以获取自己社团申请的某一活动的详情 ")
    @GetMapping("/activities/apply/{id}")
    public ResponseEntity<Object> getApplyItem(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok().body(clubService.getActivityApplyItem(id));
    }
}
