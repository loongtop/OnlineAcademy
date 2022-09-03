package com.gkhy.rbacservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.repository.UserRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/admin/user/")
public class UserController extends ControllerBase<UserRbac, Long, UserRepository> {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, RoleService roleService) {
        super(userRepository);
        this.userService = userService;
        this.roleService = roleService;
    }


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

    @PutMapping("/resetpassword")
    public Result resetPassword(@Valid @PathVariable Long id, @Valid @RequestBody JSONObject password) {
        String oldPwd = MD5.encrypt((String) password.get("oldPassword"));
        Optional<UserRbac> user = userService.findById(id);
        if (!user.isPresent()) return ItemFound.fail();

        UserRbac entity = user.get();
        if (!oldPwd.equals(entity.getPassword())) {
            return Result.fail().data("message", "Password was wrong!");
        }

        String newPassword1 = (String) password.get("password1");
        String newPassword2 = (String) password.get("password2");
        if (!newPassword1.equals(newPassword2)) {
            return Result.fail().data("message", "Passwords were different!");
        }
        entity.setPassword(MD5.encrypt((String) password.get("newPassword1")));
        UserRbac userRbac = userService.save(entity);
        return Result.success().data("message", "Passwords change successfully!");
    }
}
