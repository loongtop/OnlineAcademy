package com.gkhy.rbacservice.controller;

import com.gkhy.servicebase.user.User;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * User
 * </p>
 *
 * @author leo
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/admin/User")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("all")
    public Result findAllUser() {
        //Call the method of service to query all operations
        List<User> UserList = userService.findAll();
        return Result.success().data("items", UserList);
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Optional<User> User = userService.findById(id);
        return Result.success().data("item", User);
    }

    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        User User) {
        return Result.success();
    }

    @PostMapping("save")
    public Result save(@RequestBody User User) {
        User.setPassword(MD5.encrypt(User.getPassword()));
        User entity = userService.save(User);
        return Result.success().data("item", entity);

    }

    @PutMapping("update")
    public Result updateById(@RequestBody User User) {
        User entity = userService.save(User);
        return Result.success().data("item", entity);
    }


    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        userService.removeByIds(idList);
        return Result.success();
    }

    @GetMapping("/toAssign/{UserId}")
    public Result toAssign(@PathVariable Long UserId) {
        Optional<Role> roleMap = roleService.findById(UserId);
        return Result.success();
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long UserId,@RequestParam Long[] roleId) {
        roleService.saveUserRoleRelationShip(UserId,roleId);
        return Result.success();
    }
}
