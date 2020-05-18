package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.cms.model.CmsClubExample;
import edu.fzu.zhishe.core.dto.SysAdminAuditedDTO;
import edu.fzu.zhishe.core.dto.SysAdminClubSpeciesDTO;
import edu.fzu.zhishe.core.dto.SysAdminNewUsersDTO;
import edu.fzu.zhishe.core.service.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */

@RestController
@Api(tags = "AdminHomeController")
@RequestMapping("/admin")
public class AdminHomeController {

    @Autowired
    private SysAdminService adminService;

    @ApiOperation(" 待审核事项 ")
    @GetMapping("/unaudited")
    public ResponseEntity<Object> unaudited() {
        return ResponseEntity.ok(adminService.unaudited());
    }

    /*
     * TODO
     */
    //@ApiOperation(" 注册人数 ")
    @GetMapping("/newusers")
    public ResponseEntity<Object> newUsers() {


        return ResponseEntity.ok(adminService.newUsers());
    }

    /*
     * TODO
     */
    //@ApiOperation(" 社团类别数 ")
    @GetMapping("/clubspecies")
    public ResponseEntity<Object> clubSpecies() {
        CmsClubExample example = new CmsClubExample();


        return ResponseEntity.ok(adminService.clubSpecies());
    }


}
