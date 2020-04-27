package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import edu.fzu.zhishe.core.service.CmsBulletinService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@RestController
@RequestMapping("/clubs")
public class BulletinController {

    @Autowired
    private CmsBulletinService bulletinService;

    @ApiOperation(" 发布公告 ")
    @PostMapping("/{club}/bulletins")
    public ResponseEntity<Object> createBulletin(@RequestBody CmsBulletinParam bulletinParam,
        @PathVariable("club") Integer clubId) {
        return null;
    }

    @ApiOperation(" 查看某个社团所有公告 ")
    @GetMapping("/{club}/bulletins")
    public ResponseEntity<Object> createBulletin(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "limit", defaultValue = "3") Integer limit,
        @PathVariable("club") Integer clubId) {

        List<CmsBulletin> bulletins = bulletinService.listClubBulletin(clubId, page, limit);
        return ResponseEntity.ok().body(bulletins);
    }
}
