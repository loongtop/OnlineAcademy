package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.permission.Permission;
import com.gkhy.rbacservice.repository.RoleRepository;
import com.gkhy.rbacservice.service.PermissionService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin/role")
public class RoleController extends ControllerBase<Role, RoleRepository> {

    private final RoleService roleService;
    private final PermissionService permissionService;

    public RoleController(RoleRepository roleRepository, RoleService roleService, PermissionService permissionService) {
        super(roleRepository);
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @PostMapping("/addPermissions/{id}")
    public Result addPermissions(@PathVariable @Min(1) Long id, @RequestParam("ids") @NotNull List<Long> ids) {
        Optional<Role> role = roleService.findById(id);
        if (role.isEmpty()) return ItemFound.fail();

        List<Permission> permissionList = permissionService.findAllById(ids);

        if (permissionList.size() <= 0) return ItemFound.fail();

        Role entity = role.get();
        permissionList.forEach(p->{
            entity.getPermissions().add(p);
            p.getRoles().add(entity);
            permissionService.save(p);
        });

        roleService.save(entity);
        return Result.success().data("item", entity);
    }

    @DeleteMapping("/removePermissions/{id}")
    public Result removePermissions(@PathVariable @Min(1) Long id, @Valid @RequestParam("ids") @NotNull List<Long> ids) {
        Optional<Role> role = roleService.findById(id);
        if (role.isEmpty()) return ItemFound.fail();

        Role entity = role.get();
        Set<Permission> permissionSet = entity.getPermissions();
        List<Permission> permissionList = permissionService.findAllById(ids);

        if (permissionList.size() <= 0) return ItemFound.fail();

        permissionList.forEach(p -> {
            p.getRoles().remove(entity);
            permissionService.save(p);
            permissionSet.remove(role);
        });

        roleService.save(entity);
        return Result.success().data("item", entity);
    }
}
