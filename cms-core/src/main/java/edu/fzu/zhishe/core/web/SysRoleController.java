package edu.fzu.zhishe.core.web;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.core.service.SysRoleService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@RestController
@ApiModel(" 角色管理 ")
@RequestMapping("/roles")
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

    // TODO: api operation
    @ApiOperation(" 根据所在社团切换角色 ")
    @PostMapping("/switch")
    // @PreAuthorize("hasAuthority('sys:perm:read')")
    public ResponseEntity<Object> switchRole(@RequestBody JSONObject object) {
        Integer clubId = (Integer) object.get("clubId");
        roleService.switchCurrentRole(clubId);
        return ResponseEntity.noContent().build();
    }
}
