package edu.fzu.zhishe.core.web;

import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import edu.fzu.zhishe.common.api.CommonPage;
import edu.fzu.zhishe.core.dto.CreditDTO;
import edu.fzu.zhishe.core.param.CmsClubsCreationsParam;
import edu.fzu.zhishe.core.param.CreditForCheckinParam;
import edu.fzu.zhishe.core.service.CreditService;
import edu.fzu.zhishe.core.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yang on 5/18/2020.
 * @version 1.0
 */
@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    CreditService creditService;

    @Autowired
    private CmsUserClubRelMapper cmsUserClubRelMapper;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(" 10.1签到获取积分 ")
    @PostMapping("/{clubId}/checkin")
    public ResponseEntity<Object> checkin( @PathVariable("clubId") Integer clubId){
        creditService.checkin(clubId,new Date());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(" 10.2签到状态 ")
    @GetMapping("/{clubId}/ischeckin")
    public ResponseEntity<Object> isCheckin( @PathVariable("clubId") Integer clubId){
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andUserIdEqualTo(sysUserService.getCurrentUser().getId())
                .andClubIdEqualTo(clubId);
        List<CmsUserClubRel> userClubRelList = cmsUserClubRelMapper.selectByExample(example);
        int state = creditService.isCheckin(clubId,new Date(),userClubRelList);
        return ResponseEntity.ok().body(new CreditDTO(state));
    }

    @ApiOperation(" 10.3获取当前用户活跃度 ")
    @GetMapping("/{clubId}/userhonor")
    public ResponseEntity<Object> getUserHonor( @PathVariable("clubId") Integer clubId){
        return ResponseEntity.ok().body(creditService.getUserHonor(clubId));
    }

    @ApiOperation(" 10.5获取用户活跃度规则信息 ")
    @GetMapping("/userhonor")
    public ResponseEntity<Object> getCreditRuleForUser(){

        return ResponseEntity.ok().body(creditService.listMemberHonor());
    }

    @ApiOperation(" 10.6获取社团等级规则信息 ")
    @GetMapping("/clubhonor")
    public ResponseEntity<Object> getCreditRuleForClub(){

        return ResponseEntity.ok().body(creditService.listClubGrade());
    }
}
