package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.core.param.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.param.CreditForCheckinParam;
import edu.fzu.zhishe.core.service.CreditService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yang on 5/18/2020.
 * @version 1.0
 */
@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    CreditService creditService;

    @ApiOperation(" 10.1签到获取积分 ")
    @PostMapping("/{clubId}/checkin")
    public ResponseEntity<Object> createClub( @PathVariable("clubId") Integer clubId){
        creditService.checkin(clubId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
