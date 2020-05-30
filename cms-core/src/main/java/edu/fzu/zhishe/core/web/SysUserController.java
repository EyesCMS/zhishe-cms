package edu.fzu.zhishe.core.web;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.common.api.ErrorResponse;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.UpdatePasswordResultEnum;
import edu.fzu.zhishe.core.dto.*;
import edu.fzu.zhishe.core.error.UserErrorEnum;
import edu.fzu.zhishe.core.param.SysUserAnswerParam;
import edu.fzu.zhishe.core.param.SysUserUpdateParam;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.cms.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理
 *
 * @author peng
 */
@Slf4j
@RestController
@Api(tags = "UserController")
@RequestMapping("/users")
public class SysUserController {

    @Autowired
    SysUserService userService;

    @ApiOperation(value = " 根据用户名获取密保问题 ")
    @GetMapping(value = "/question")
    public ResponseEntity<Object> question(@RequestParam String username) {
        SysUser user = userService.getByUsername(username);
        if (user == null) {
            Asserts.notFound(UserErrorEnum.USERNAME_NOT_FOUND);
        }
        if (user.getLoginQuestion() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.message("用户未设置密保"));
        }
        Map<String, String> myMap = new HashMap<>(1);
        myMap.put("loginProblem", user.getLoginQuestion());
        return ok(myMap);
    }

    @ApiOperation(value = " 校验密保问题回答是否正确 ")
    @PostMapping(value = "/answer")
    public ResponseEntity<Object> answer(@Validated @RequestBody SysUserAnswerParam param) {

        if (param.getUsername() == null) {
            Asserts.notFound(UserErrorEnum.USERNAME_NOT_FOUND);
        }
        SysUser user = userService.getByUsername(param.getUsername());
        if (user != null && user.getLoginAnswer() != null && user.getLoginAnswer().equals(param.getAnswer())) {
            return noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @ApiOperation(value = " 用户修改个人信息 ")
    @PutMapping(value = "/info")
    public ResponseEntity<Object> info(@Validated @RequestBody SysUserUpdateParam updateParam) {

        userService.updateUserByParam(updateParam);
        return noContent().build();
    }

    @ApiOperation(value = " 用户修改头像 ")
    @PostMapping(value = "/avatar")
    public ResponseEntity<Object> avatar(@RequestParam("image") MultipartFile image) {

        String url = userService.updateAvatar(image);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("avatarUrl", url);
        return ok().body(jsonObject);
    }

    @ApiOperation(value = " 忘记密码时通过回答保密问题修改密码 ")
    @PutMapping(value = "/password")
    public ResponseEntity<Object> password(@Validated @RequestBody SysUserUpdatePwdByAnswer param) {

        UpdatePasswordResultEnum result = userService.updateUserPasswordAfterAnswer(param);

        if (result != UpdatePasswordResultEnum.SUCCESS) {
            return ResponseEntity.badRequest().body(ErrorResponse.message(result.getMessage()));
        }
        return noContent().build();
    }
}
