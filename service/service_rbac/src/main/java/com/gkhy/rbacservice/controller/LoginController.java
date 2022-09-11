package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.error.RBACError;
import com.gkhy.rbacservice.entity.request.LoginRequest;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.result.status.StatusCode;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping
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
    @NotControllerResponseAdvice
    @PostMapping(path = "/login")
    public Object login(@Validated LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        String remember = Objects.requireNonNullElse(loginRequest.getRemember(), "");

        Optional<UserRbac> userRbac = userService.findOneByColumnName("email", email);
        if (userRbac.isEmpty()) return ItemFound.fail().data("message", RBACError.LOGIN_FAILED);

        UserRbac user = userRbac.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return Result.fail().codeAndMessage(RBACError.EMAIL_OR_PASSWORD_WRONG);
        }

        if (!user.getEmailVerified())
            return "Go to verified you email with the code!";

        List<String> permissions = null;
        for (Role role : user.getRoles()) {
        }

        redisService.set(user.getName() + user.getEmail(), "permissions");

        if (remember.equals("on")) {
            redisService.set("remember", "remember");
        }

        ModelAndView modelAndView = new ModelAndView("/home1.html");
//        modelAndView.setViewName("/layout");
        modelAndView.addObject("info", "leo");
        modelAndView.addObject("rbacUser", user);
//        modelAndView.setViewName();

        return modelAndView;
    }
}
