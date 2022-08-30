package com.gkhy.rbacservice.controller;

import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.user.IUserRepository;
import com.gkhy.servicebase.user.User;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 * UserController
 * </p>
 *
 * @author leo
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/admin/rbac/user/")
public class UserController extends ControllerBase<User, Long, IUserRepository> {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(IUserRepository iUserRepository, UserService userService, RoleService roleService) {
        super(iUserRepository);
        this.userService = userService;
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

    @GetMapping("/toAssign/{UserId}")
    public Result toAssign(@PathVariable Long UserId) {
        Optional<Role> roleMap = roleService.findById(UserId);
        return Result.success();
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long UserId, @RequestParam Long[] roleId) {
        roleService.saveUserRoleRelationShip(UserId,roleId);
        return Result.success();
    }
}
