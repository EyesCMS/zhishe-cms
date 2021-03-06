package edu.fzu.zhishe.core.web;

import static org.springframework.http.ResponseEntity.ok;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.IsClubMember;
import edu.fzu.zhishe.core.dto.CmsBulletinDTO;
import edu.fzu.zhishe.core.dto.CmsBulletinsDTO;
import edu.fzu.zhishe.core.error.BulletinErrorEnum;
import edu.fzu.zhishe.core.param.CmsBulletinParam;
import edu.fzu.zhishe.core.param.CmsBulletinQuery;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsBulletinService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zou
 * <p>
 * 公告模块控制层
 */
@RestController
@RequestMapping("/clubs")
public class BulletinController {

    @Autowired
    private CmsBulletinService bulletinService;

    @ApiOperation(" 5.1发布公告 ")
    @PostMapping("/{club}/bulletins")
    public ResponseEntity<Object> createBulletin(
        @Validated @RequestBody CmsBulletinParam bulletinParam,
        @PathVariable("club") Integer clubId) {

        if (bulletinService.creatBulletin(clubId, bulletinParam) == 0) {
            Asserts.fail(BulletinErrorEnum.CLUB_NOT_EXIST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 5.2查看某个社团所有公告 ")
    @GetMapping("/{club}/bulletins")
    @IsClubMember
    public ResponseEntity<Object> listClubBulletin(
        @Validated PaginationParam paginationParam,
        CmsBulletinQuery bulletinQuery,
        @PathVariable("club") Integer clubId) {

        List<CmsBulletinsDTO> bulletinList = null;
        bulletinList = bulletinService.listClubBulletin(clubId,paginationParam,bulletinQuery);
        return ResponseEntity.ok().body(CommonPage.restPage(bulletinList));
    }

    @ApiOperation("5.3查看公告详情")
    @GetMapping("/{club}/bulletins/{bulletinId}")
    @IsClubMember
    public ResponseEntity<CmsBulletinDTO> getBulletin(
        @PathVariable("club") Integer clubId,
        @PathVariable("bulletinId") Integer bulletinId) {

        CmsBulletin bulletin = bulletinService.getBulletin(clubId, bulletinId);
        if (bulletin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CmsBulletinDTO bulletinDTO = new CmsBulletinDTO();
        BeanUtils.copyProperties(bulletin, bulletinDTO);
        bulletinDTO.setBody(bulletin.getBody());
        return ok().body(bulletinDTO);
    }

    @ApiOperation("5.4修改公告内容")
    @PutMapping("/{club}/bulletins/{bulletinId}")

    public ResponseEntity<Object> updateBulletin(
        @Validated @RequestBody CmsBulletinParam bulletinParam,
        @PathVariable("club") Integer clubId,
        @PathVariable("bulletinId") Integer bulletinId) {

        if (bulletinService.getBulletin(clubId,bulletinId) == null) {
            Asserts.fail(BulletinErrorEnum.BULLETIN_NOT_EXIST);
        }

        if (bulletinService.updateBulletin(bulletinId, bulletinParam) == 0) {
            Asserts.fail(BulletinErrorEnum.CAN_NOT_UPDATE_BULLETIN);
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("5.5删除公告")
    @DeleteMapping("/bulletins/{bulletinId}")
    public ResponseEntity<Object> deleteBulletin(@PathVariable("bulletinId") Integer bulletinId) {

        if (bulletinService.deleteBulletin(bulletinId) == 0) {
            Asserts.fail(BulletinErrorEnum.CAN_NOT_DELETE_BULLETIN);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
