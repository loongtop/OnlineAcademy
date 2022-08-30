package com.gkhy.rbacservice.controller;


import com.gkhy.rbacservice.repository.PermissionRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.Permission;
import com.gkhy.rbacservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rbac/permission")
public class PermissionController extends ControllerBase<Permission, Long, PermissionRepository> {

    private final PermissionService permissionService;
    @Autowired
    public PermissionController(PermissionRepository permissionRepository, PermissionService permissionService) {
        super(permissionRepository);
        this.permissionService = permissionService;
    }

    /**
     * Extends these methods from the class ControllerBase
     *
     *    //Query all rows(data) from the table
     *     @GetMapping("/all")
     *     public Result findAll() ;
     *
     *     //Add a record(row) to the table
     *     @PostMapping("/add")
     *     public Result add(@RequestBody Object o) ;
     *
     *     //Save method
     *     @PostMapping("/save")
     *     public Result save(@RequestBody T t) ;
     *
     *     //Query by id
     *     @GetMapping("/get/{id}")
     *     public Result getById(@PathVariable E id) ;
     *
     *     //update a record(row)
     *     @PostMapping("/update/{id}")
     *     public Result update(@PathVariable E id, @RequestBody Object o)
     *
     *     //logically remove a record(row)
     *     @DeleteMapping("/remove/{id}")
     *     public Result remove(@PathVariable E id) ;
     *
     *     //logically remove records(rows)
     *     @DeleteMapping("/batchRemove")
     *     public Result removeByIds(@RequestParam("ids") List<E> ids) ;
     *
     *     //delete a record(row) from the table, Unable to restore
     *     @DeleteMapping("/delete/{id}")
     *     public Result delete(@PathVariable E id) ;
     *
     *     //delete records(rows) from the table, Unable to restore
     *     @DeleteMapping("/batchDelete")
     *     public Result deleteByIds(@RequestParam("ids") List<E> ids);
     *
     *     //Method for querying lecturers by page
     *     //current page
     *     //the limit of the number of items
     *     @GetMapping("page/{current}/{limit}")
     *     public Result getByPage(@PathVariable int current, @PathVariable int limit);
     * */

    @GetMapping
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
