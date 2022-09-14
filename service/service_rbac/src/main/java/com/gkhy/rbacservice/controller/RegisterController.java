package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.request.RegisterRequest;
import com.gkhy.rbacservice.error.RBACError;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.result.Result;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @Name: RegisterController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-06
 * @Updated: 2022-09-06
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/user")
public class RegisterController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder, RedisService redisService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
    }

    //register a user
    @NotControllerResponseAdvice
    @PostMapping(path = "/register")
    public Object register(@Valid RegisterRequest registerRequest) {

        String password1 = registerRequest.getPassword1();
        String password2 = registerRequest.getPassword2();
        if (!password1.equals(password2)) {
            return Result.fail().codeAndMessage(RBACError.PASSWORDS_DIFFERENT_ERROR);
        }

        String email  = registerRequest.getEmail();
        String name = registerRequest.getName();
        String remember = Objects.requireNonNullElse(registerRequest.getRemember(), "");

        if(userService.existsByColumnName("email", email)) {
            return Result.fail().data("message", RBACError.EMAIL_OCCUPIED);
        }

        // Creating a user's account
        UserRbac user = new UserRbac();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password1));
        user.setEnabled(true);
        userService.save(user);

        if (remember.equals("on")) {
            redisService.set("remember", "remember");
        }

        return "redirect:/loginp.html";
    }

}
