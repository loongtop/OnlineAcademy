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
 * User
 * </p>
 *
 * @author leo
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/admin/user")
public class UserController extends ControllerBase<User, Long, IUserRepository> {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(IUserRepository iUserRepository, UserService userService, RoleService roleService) {
        super(iUserRepository);
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        User User) {
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
