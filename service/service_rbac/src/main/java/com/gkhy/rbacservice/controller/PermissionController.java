package com.gkhy.rbacservice.controller;


import com.gkhy.rbacservice.repository.PermissionRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.permission.Permission;
import com.gkhy.rbacservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/permission")
public class PermissionController extends ControllerBase<Permission, Long, PermissionRepository> {

    private final PermissionService permissionService;
    @Autowired
    public PermissionController(PermissionRepository permissionRepository, PermissionService permissionService) {
        super(permissionRepository);
        this.permissionService = permissionService;
    }

    @GetMapping("/query")
    public Result indexAllPermissionEntity() {
        List<Permission> list = permissionService.queryAllMenu();
        return Result.success().data("children", list);
    }

    @PostMapping("/doAssign")
    public Result doAssign(Long roleId, Long[] PermissionEntityId) {
        permissionService.saveRolePermissionEntityRelationShip(roleId, PermissionEntityId);
        return Result.success();
    }

    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Result.success().data("children", list);
    }

}
