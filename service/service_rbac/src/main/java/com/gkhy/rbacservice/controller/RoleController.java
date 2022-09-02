package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.repository.RoleRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/rbac/role")
public class RoleController extends ControllerBase<Role, Long, RoleRepository> {

    private final RoleService roleService;
    @Autowired
    public RoleController(RoleRepository roleRepository, RoleService roleService) {
        super(roleRepository);
        this.roleService = roleService;
    }

    /**
     * Extends these methods from the class ControllerBase
     *
     *    //Query all rows(data) from the table
     *     @GetMapping("/all")
     *     public Result findAll() ;
     *
     *     //Add a record(row) to the table
     *     @PutMapping("/add")
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


}
