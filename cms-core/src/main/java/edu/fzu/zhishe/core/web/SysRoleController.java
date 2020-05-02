package edu.fzu.zhishe.core.web;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.core.constant.RoleSwitchDirectionEnum;
import edu.fzu.zhishe.core.service.SysRoleService;
import edu.fzu.zhishe.core.validator.FlagValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@RestController
@ApiModel(" 角色管理 ")
@RequestMapping("/roles")
@Validated
public class SysRoleController {

    private final SysRoleService roleService;

    public SysRoleController(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(" 根据角色 ID 获取权限列表 ")
    @GetMapping("/{id}/permissions")
    @PreAuthorize("hasAuthority('sys:perm:read')")
    public ResponseEntity<Object> listRolePermissions(@PathVariable Integer id) {
        return ResponseEntity.ok().body(roleService.listPermission(id));
    }

    @ApiOperation(" 根据所在社团切换角色 ")
    @PostMapping("/switch")
    // @PreAuthorize("hasAuthority('sys:perm:read')")
    public ResponseEntity<Object> switchRole(Principal principal,
            @FlagValidator(value = {"0", "1"}, message = "type can only be 0 or 1") @RequestParam(value = "type") Integer direction,
            @RequestBody JSONObject object, BindingResult bindingResult) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (direction == RoleSwitchDirectionEnum.USER_TO_CLUB.getValue()) {
            Integer clubId = (Integer) object.get("clubId");
            roleService.switchCurrentRole(clubId);
        } else {
            roleService.resetCurrentRole();
        }

        return ResponseEntity.noContent().build();
    }
}
