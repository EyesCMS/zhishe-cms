package edu.fzu.zhishe.core.web;


import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.service.CmsClubService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *社团管理
 *
 * @author yang
 */
@RestController
@RequestMapping("/clubs")
public class CmsClubController {

    private final CmsClubService clubService;


    public CmsClubController(CmsClubService clubService) {
        this.clubService = clubService;
    }

    @ApiOperation(" 4.1提交创建社团申请表单 ")
    @PostMapping("/creations")
    public ResponseEntity<Object> clubCreate(@RequestBody CmsClubsCreationsParam cmsClubsCreationsParam){
        clubService.clubCreate(cmsClubsCreationsParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 4.2社团创建申请列表 ")
    @GetMapping("/creations")
    public ResponseEntity<Object> clubList(){
        List<CmsClubCreateApply> clubCreateApplyList = clubService.getClubCreateList();
        return ResponseEntity.ok().body(clubCreateApplyList);
    }

}
