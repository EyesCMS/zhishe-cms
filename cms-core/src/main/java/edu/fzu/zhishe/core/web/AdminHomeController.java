package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.core.dto.SysAdminAuditedDTO;
import edu.fzu.zhishe.core.dto.SysAdminClubSpeciesDTO;
import edu.fzu.zhishe.core.dto.SysAdminNewUsersDTO;
import io.swagger.annotations.Api;
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

    /*
     * TODO
     */
    //@ApiOperation(" 待审核事项 ")
    @GetMapping("/audited")
    public ResponseEntity<Object> audited() {


        return ResponseEntity.ok(new SysAdminAuditedDTO());
    }

    /*
     * TODO
     */
    //@ApiOperation(" 注册人数 ")
    @GetMapping("/newusers")
    public ResponseEntity<Object> newUsers() {


        return ResponseEntity.ok(new SysAdminNewUsersDTO());
    }

    /*
     * TODO
     */
    //@ApiOperation(" 社团类别数 ")
    @GetMapping("/clubspecies")
    public ResponseEntity<Object> clubSpecies() {


        return ResponseEntity.ok(new SysAdminClubSpeciesDTO());
    }


}
