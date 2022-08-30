package com.gkhy.rbacservice.controller;


import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.Permission;
import com.gkhy.rbacservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {
    
    private final PermissionService permissionService;
    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public Result indexAllPermissionEntity() {
        List<Permission> list =  permissionService.queryAllMenu();
        return Result.success().data("children",list);
    }
    
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removeChildById(id);
        return Result.success();
    }
    
    @PostMapping("/doAssign")
    public Result doAssign(Long roleId,Long[] PermissionEntityId) {
        permissionService.saveRolePermissionEntityRelationShip(roleId,PermissionEntityId);
        return Result.success();
    }
    
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Result.success().data("children", list);
    }
    
    @PostMapping("save")
    public Result save(@RequestBody Permission permissionEntity) {

        Permission entity = permissionService.save(permissionEntity);
        return Result.success().data("item", entity);

    }
    
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permissionEntity) {
        Permission entity = permissionService.save(permissionEntity);
        return Result.success().data("item", entity);
    }
}
