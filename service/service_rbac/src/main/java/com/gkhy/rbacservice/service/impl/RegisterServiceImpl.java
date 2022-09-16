package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.request.RegisterRequest;
import com.gkhy.rbacservice.error.RBACError;
import com.gkhy.rbacservice.service.RegisterService;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.result.Result;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * @Name: RegisterServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-16
 * @Updated: 2022-09-16
 * @Version: 1.0
 **/
@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    public RegisterServiceImpl(UserService userService, PasswordEncoder passwordEncoder, RedisService redisService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
    }

    @SneakyThrows
    @Override
    public Object register(RegisterRequest registerRequest, ModelAndView responseModel) {

        String password1 = registerRequest.getPassword1();
        String password2 = registerRequest.getPassword2();

        Result r = checkTwoPasswords(password1, password2);
        if (r.isFail()) return r;

        if(userService.existsByColumnName("email", registerRequest.getEmail())) {
            return Result.fail().data("message", RBACError.EMAIL_OCCUPIED);
        }

        // Creating a user's account
        UserRbac user = new UserRbac();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(password1));
        userService.save(user);

        String remember = Objects.requireNonNullElse(registerRequest.getRemember(), "");
        if (remember.equals("on")) {
            redisService.set("remember", "remember");
        }

        return responseModel;
    }

    public Result checkTwoPasswords(String password1, String password2) {

        if (password1.equals(password2)) return Result.success();

        return Result.fail().codeAndMessage(RBACError.PASSWORDS_DIFFERENT_ERROR);
    }
}
