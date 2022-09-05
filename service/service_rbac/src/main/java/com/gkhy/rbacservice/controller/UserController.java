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
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 * UserController
 * </p>
 *
 * @author leo
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/admin/user")
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

    @PutMapping("/resetpassword/{id}")
    public Result resetPassword(@Valid @PathVariable Long id, @Valid @RequestBody JSONObject password) {
        String oldPwd = MD5.encrypt((String) password.get("PASSWORD0"));
        Optional<UserRbac> user = userService.findById(id);
        if (!user.isPresent()) return ItemFound.fail();

        UserRbac entity = user.get();
        if (!oldPwd.equals(entity.getPassword())) {
            return Result.fail().data("message", "Password was wrong!");
        }

        String newPassword1 = (String) password.get("PASSWORD1");
        String newPassword2 = (String) password.get("PASSWORD2");
        if (!newPassword1.equals(newPassword2)) {
            return Result.fail().data("message", "Passwords were different!");
        }
        entity.setPassword(MD5.encrypt((String) password.get("PASSWORD1")));
        UserRbac userRbac = userService.save(entity);
        return Result.success().data("message", "Passwords change successfully!");
    }

    @PostMapping("/addRoles/{id}")
    public Result addRoles(@Valid @PathVariable Long id, @Valid @RequestParam("ids") List<Long> ids) {
        Optional<UserRbac> user = userService.findById(id);
        if (user.isEmpty()) ItemFound.fail().data("message", "Can not find Group in the database!");

        List<Role> roleList = roleService.findAllById(ids);
        UserRbac entity = user.get();
        roleList.forEach(r->{
            entity.getRoles().add(r);
            r.getUser().add(entity);
            roleService.save(r);
        });

        userService.save(entity);

        return Result.success().data("item", entity);
    }

    @DeleteMapping("/removeRoles/{id}")
    public Result removeRoles(@Valid @PathVariable Long id, @Valid @RequestParam("ids") List<Long> ids) {
        Optional<UserRbac> user = userService.findById(id);
        if (user.isEmpty()) ItemFound.fail().data("message", "Can not find user in the database!");

        UserRbac entity = user.get();
        Set<Role> roleSet = entity.getRoles();
        List<Role> roleList = roleService.findAllById(ids);
        roleList.forEach(role -> {
            role.getUser().remove(entity);
            roleService.save(role);
            roleSet.remove(role);
        });

        userService.save(entity);
        return Result.success().data("item", entity);
    }

}
