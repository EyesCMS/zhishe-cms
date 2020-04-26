package edu.fzu.zhishe.core.web;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import edu.fzu.zhishe.common.api.AjaxResponse;
import edu.fzu.zhishe.common.api.Error;
import edu.fzu.zhishe.common.api.ErrorResponseBody;
import edu.fzu.zhishe.common.exception.ApiException;
import edu.fzu.zhishe.core.constant.UpdatePasswordResultEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.SysUserLoginParam;
import edu.fzu.zhishe.core.dto.SysUserRegisterParam;
import edu.fzu.zhishe.core.dto.UpdateUserPasswordParam;
import edu.fzu.zhishe.core.service.SysUserService;
import edu.fzu.zhishe.cms.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    public AuthController(SysUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(" 用户注册 ")
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody SysUserRegisterParam userRegisterParam) {
        try {
            userService.register(userRegisterParam);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (ApiException a) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @ApiOperation("  登录以后返回 token ")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody SysUserLoginParam userLoginParam) {
        String token = userService
            .login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ok(tokenMap);
    }

    @ApiOperation(value = " 登出功能 ")
    @PostMapping(value = "/logout")
    public ResponseEntity<Object> logout() {
        SysUser currentUser = userService.getCurrentUser();
        // 不是管理员时，重置当前角色
        if (currentUser.getIsAdmin() == 0) {
            currentUser.setCurrentRole(UserRoleEnum.NORMAL.getValue());
            userService.updateUserSelective(currentUser);
        }
        return noContent().build();
    }

    @ApiOperation(" 获取用户信息 ")
    @GetMapping("/info")
    public ResponseEntity<Object> info(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponseBody.unauthorized());
        }
        //SysUser user = userService.getCurrentMember();
        String username = principal.getName();
        SysUser user = userService.getByUsername(username);

        // MUST: reset current user role
        user.setCurrentRole(UserRoleEnum.NORMAL.getValue());
        userService.updateUserSelective(user);

        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("avatar", user.getAvatarUrl());
        if (user.getIsAdmin() == 1) {
            data.put("roles", new String[]{"admin"});
        } else {
            data.put("roles", new String[]{"normal"});
        }
        return ok().body(data);
    }

    @PreAuthorize("hasAuthority('sys:user:read')")
    @ApiOperation(" 获取用户列表 ")
    @GetMapping("/users")
    public ResponseEntity<Object> users() {
        List<SysUser> users = userService.users();
        List<SysUser> userList = users.stream().limit(3).collect(Collectors.toList());
        return ok().body(userList);
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
            Error error = new Error("", "", "");
            AjaxResponse response = new AjaxResponse();
            response.setMessage(result.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return noContent().build();
    }

//    @ApiOperation(value = " 刷新 token ")
//    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
//    public CommonResult refreshToken(HttpServletRequest request) {
//        String token = request.getHeader(tokenHeader);
//        String refreshToken = memberService.refreshToken(token);
//        if (refreshToken == null) {
//            return CommonResult.failed("token 已经过期！");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", refreshToken);
//        tokenMap.put("tokenHead", tokenHead);
//        return CommonResult.success(tokenMap);
//    }
}
