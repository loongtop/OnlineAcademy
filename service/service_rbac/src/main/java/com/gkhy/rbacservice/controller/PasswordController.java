package com.gkhy.rbacservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.commonutils.encryption.MD5;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

/**
 * @Name: PasswordController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-05
 * @Updated: 2022-09-05
 * @Version: 1.0
 **/
@Validated
@RestController
@RequestMapping("/admin/password")
public class PasswordController {

    private final UserService userService;
    private final RedisService redisService;
    public PasswordController(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @PutMapping("/resetById/{id}")
    public Result resetById(@PathVariable @Min(1) Long id, @RequestBody @NotNull JSONObject password) {

        Optional<UserRbac> user = userService.findById(id);
        if (!user.isPresent()) return ItemFound.fail();

        return resetPwd(user.get(), password);
    }

    @PutMapping("/resetByEmail")
    public Result resetByEmail(@RequestBody @NotNull JSONObject password) {

        if (!password.containsKey("EMAIL") || !password.containsKey("EMAILCODE")) {
            return Result.fail().data("message", "Email or Code error!");
        }

        String codeRedis = String.valueOf(redisService.get("EMAILCODE"));
        if (!codeRedis.equals(password.get("EMAILCODE"))) {
            return Result.fail().data("message", "Email Code is different!");
        }

        String email = String.valueOf(password.get("EMAIL"));

        Optional<UserRbac> user = userService.findOneByColumnName("email", email);
        if (!user.isPresent()) return ItemFound.fail();

        return resetPwd(user.get(), password);
    }

    private Result resetPwd(@NotNull UserRbac user, @NotNull JSONObject password) {

        String oldPwd = MD5.encrypt((String) password.get("PASSWORD0"));

        if (!oldPwd.equals(user.getPassword())) {
            return Result.fail().data("message", "Password was wrong!");
        }

        String newPassword1 = (String) password.get("PASSWORD1");
        String newPassword2 = (String) password.get("PASSWORD2");
        if (!newPassword1.equals(newPassword2)) {
            return Result.fail().data("message", "Passwords were different!");
        }
        user.setPassword(MD5.encrypt((String) password.get("PASSWORD1")));
        UserRbac userRbac = userService.save(user);
        return Result.success().data("message", "Passwords change successfully!");
    }

}
