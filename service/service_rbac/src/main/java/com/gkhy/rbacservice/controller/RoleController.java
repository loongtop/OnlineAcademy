package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.Permission;
import com.gkhy.rbacservice.repository.RoleRepository;
import com.gkhy.rbacservice.service.PermissionService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin/role")
public class RoleController extends ControllerBase<Role, Long, RoleRepository> {

    private final RoleService roleService;
    private final PermissionService permissionService;

    public RoleController(RoleRepository roleRepository, RoleService roleService, PermissionService permissionService) {
        super(roleRepository);
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @PostMapping("/addPermissions/{id}")
    public Result addPermissions(@Valid @PathVariable Long id, @Valid @RequestParam("ids") List<Long> ids) {
        Optional<Role> role = roleService.findById(id);
        if (role.isEmpty()) ItemFound.fail().data("message", "Can not find Role in the database!");

        List<Permission> permissionList = permissionService.findAllById(ids);
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
    public Result removePermissions(@Valid @PathVariable Long id, @Valid @RequestParam("ids") List<Long> ids) {
        Optional<Role> role = roleService.findById(id);
        if (role.isEmpty()) ItemFound.fail().data("message", "Can not find Role in the database!");

        Role entity = role.get();
        Set<Permission> permissionSet = entity.getPermissions();
        List<Permission> permissionList = permissionService.findAllById(ids);
        permissionList.forEach(p -> {
            p.getRoles().remove(entity);
            permissionService.save(p);
            permissionSet.remove(role);
        });

        roleService.save(entity);
        return Result.success().data("item", entity);
    }
}
