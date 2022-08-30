package com.gkhy.rbacservice.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        Role role) {

        if(!StringUtils.isEmpty(role.getRoleName())) {

        }
        return Result.success();
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        long c = roleService.count();
        return Result.success().data("item", role);
    }

    @PostMapping("save")
    public Result save(@RequestBody Role role) {

        Role entity = roleService.save(role);
        return Result.success().data("item", entity);
    }

    @PutMapping("update")
    public Result updateById(@RequestBody Role role) {
        Role entity = roleService.update(role);
        return Result.success().data("item", entity);
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        roleService.removeByIds(idList);
        return Result.success();
    }
}
