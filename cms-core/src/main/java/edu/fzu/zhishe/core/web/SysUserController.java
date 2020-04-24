package edu.fzu.zhishe.core.web;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.cms.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author peng
 */

@RestController
@Api(tags = "UserController")
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @ApiOperation(value = " 根据用户 ID 获取密保问题 ")
    @GetMapping(value = "/{uid}/question")
    public ResponseEntity<Object> question(@PathVariable("uid") Integer uid) {
        SysUser user = userService.getById(uid);

        Map<String, String> myMap = new HashMap<>();
        myMap.put("login_problem", user.getLoginQuestion());
        return ok(myMap);
    }

    @ApiOperation(value = " 校验密保问题回答是否正确 ")
    @PostMapping(value = "/{uid}/answer")
    public ResponseEntity<Object> answer(@RequestParam("uid") Integer uid,
                                         @RequestParam("answer") String ans ) {
        SysUser user = userService.getById(uid);
        if (user.getLoginAnswer() != null && user.getLoginAnswer().equals(ans)) {
            return noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
