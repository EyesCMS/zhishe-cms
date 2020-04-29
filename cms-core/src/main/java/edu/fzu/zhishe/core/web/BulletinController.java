package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import edu.fzu.zhishe.core.service.CmsBulletinService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zou
 *
 * 公告模块控制层
 */
@RestController
@RequestMapping("/clubs")
public class BulletinController {

    @Autowired
    private CmsBulletinService bulletinService;

    @ApiOperation(" 5.1发布公告 ")
    @PostMapping("/{club}/bulletins")
    public ResponseEntity<Object> createBulletin(@Validated @RequestBody CmsBulletinParam bulletinParam,
        @PathVariable("club") Integer clubId) {

        if(bulletinService.creatBulletin(bulletinParam)==0){
            Asserts.fail("操作失败！");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 5.2查看某个社团所有公告 ")
    @GetMapping("/{club}/bulletins")
    public ResponseEntity<Object> createBulletin(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "limit", defaultValue = "3") Integer limit,
        @PathVariable("club") Integer clubId) {

        List<CmsBulletin> bulletins = bulletinService.listClubBulletin(clubId, page, limit);
        return ResponseEntity.ok().body(bulletins);
    }

    @ApiOperation("5.4修改公告内容")
    @PutMapping("/{club}/bulletins/{bulletin_id}")
    public ResponseEntity<Object> updateBulletin(
            @Validated @RequestBody CmsBulletinParam bulletinParam,
            @PathVariable("club") Integer clubId,
            @PathVariable("bulletin_id") Integer bulletinId) {

        if(bulletinService.updateBulletin(bulletinParam)==0){
            Asserts.fail("操作失败！");
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("5.3查看公告详情")
    @GetMapping("/{club}/bulletins/{bulletin_id}")
    public ResponseEntity<Object> lookupBulletin(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "3") Integer limit,
            @PathVariable("club") Integer clubId,
            @PathVariable("bulletin_id") Integer bulletinId) {

        List<CmsBulletin> bulletins2 = bulletinService.listBulletin(bulletinId,page, limit);
        return ResponseEntity.ok().body(bulletins2);
    }

    @ApiOperation("5.5删除公告")
    @DeleteMapping("/bulletins/{bulletin_id}")
    public ResponseEntity<Object> deleteBulletin(
            @PathVariable("bulletin_id") Integer bulletinId
    ) {
        if(bulletinService.deleteBulletin(bulletinId)==0){
            Asserts.fail("操作失败！");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
