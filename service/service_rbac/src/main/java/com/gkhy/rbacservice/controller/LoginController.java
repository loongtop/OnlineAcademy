package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.error.RBACError;
import com.gkhy.rbacservice.entity.request.LoginRequest;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.result.status.StatusCode;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Name: LoginController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-06
 * @Updated: 2022-09-06
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/user")
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public LoginController(UserService userService, PasswordEncoder passwordEncoder, RedisService redisService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
    }

    //user login account
    @PostMapping(path = "/login")
    public Result login(@RequestBody @Valid LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        String remember = loginRequest.getRemember();

        Optional<UserRbac> user = userService.findOneByColumnName("email", email);

        if (user.isEmpty()) return ItemFound.fail().data("message", RBACError.LOGIN_FAILED);

        UserRbac userRbac = user.get();

        if (!userRbac.getPassword().equals((passwordEncoder.encode(password)))) {
            return Result.fail().codeAndMessage(RBACError.EMAIL_OR_PASSWORD_WRONG);
        }

        List<String> permissions = null;
        for (Role role : userRbac.getRoles()) {
//            permissions.addAll(role.getPermissions()
//                    .stream().filter(Objects::nonNull)
//                    .map(permission.get)
//                    .filter(Objects::nonNull)
//                    .collect(Collectors.toList()));
        }

        redisService.set(userRbac.getName() + userRbac.getEmail(), permissions);

        if (remember.equals("on")) {
            redisService.set("remember", "remember");
        }
        return Result.success().data("message", "login");
    }

}
