package edu.fzu.zhishe.core.web;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.common.api.AjaxResponse;
import edu.fzu.zhishe.common.api.ErrorResponseBody;
import edu.fzu.zhishe.core.constant.UpdatePasswordResultEnum;
import edu.fzu.zhishe.core.dto.SysUserInfoDTO;
import edu.fzu.zhishe.core.param.SysUserLoginParam;
import edu.fzu.zhishe.core.param.SysUserRegisterParam;
import edu.fzu.zhishe.core.param.UpdateUserPasswordParam;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.cms.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员登录注册管理
 *
 * @author liang
 */
@RestController
@Api(tags = "UmsMemberController")
@RequestMapping("/auth")
public class AuthController {

    private final SysUserService userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${role.admin}")
    private String roleAdmin;
    @Value("${role.student}")
    private String roleStudent;

    public AuthController(SysUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(" 用户注册 ")
    @PostMapping("/register")
    public ResponseEntity<Object> register(
        @Validated @RequestBody SysUserRegisterParam userRegisterParam,
        BindingResult bindingResult) {

        userService.register(userRegisterParam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation("  登录以后返回 token ")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody SysUserLoginParam userLoginParam) {

        String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonObject);
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ok(tokenMap);
    }

    @ApiOperation(value = " 登出功能 ")
    @PostMapping(value = "/logout")
    public ResponseEntity<Object> logout() {
        return noContent().build();
    }

    @ApiOperation(" 获取用户信息 ")
    @GetMapping("/info")
    public ResponseEntity<Object> info(Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponseBody.unauthorized());
        }
        String username = principal.getName();
        SysUser user = userService.getByUsername(username);

        SysUserInfoDTO userInfoDTO = new SysUserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);
        userInfoDTO.setAvatar(user.getAvatarUrl());
        userInfoDTO.setUserId(user.getId());
        if (user.getIsAdmin() == 1) {
            userInfoDTO.setRoles(new ArrayList<>(Collections.singleton(roleAdmin)));
        } else {
            userInfoDTO.setRoles(new ArrayList<>(Collections.singleton(roleStudent)));
        }
        return ok().body(userInfoDTO);
    }

//    @ApiOperation(" 获取验证码 ")
//    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
//    public CommonResult getAuthCode(@RequestParam String telephone) {
//        String authCode = memberService.generateAuthCode(telephone);
//        return CommonResult.success(authCode, " 获取验证码成功 ");
//    }

    @ApiOperation(" 修改密码 ")
    @PostMapping("/password")
    public ResponseEntity<Object> updatePassword(
        @Validated @RequestBody UpdateUserPasswordParam updateUserPasswordParam) {

        UpdatePasswordResultEnum result = userService.updatePassword(updateUserPasswordParam);

        if (result != UpdatePasswordResultEnum.SUCCESS) {
            AjaxResponse response = new AjaxResponse();
            response.setMessage(result.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return noContent().build();
    }

}
