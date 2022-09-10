package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.error.RBACError;
import com.gkhy.rbacservice.service.EmailService;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Optional;

/**
 * @Name: EmailController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-09
 * @Updated: 2022-09-09
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/user/email")
@Validated
public class EmailController {

    private UserService userService;
    private EmailService emailService;

    public EmailController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/sendcode")
    public Object sendCode(@NotEmpty @RequestParam("email") String email) {
        if (userService.existsByColumnName("email", email)) {
            emailService.sendCode(email);
        }

        return ItemFound.fail();
    }

    @GetMapping("/verification")
    public Object verification(@NotEmpty @RequestParam("verification") String verification) {
        String email = Objects.requireNonNull(checkVerification(verification), "");

        if (StringUtils.isEmpty(email)) {
            return Result.fail().codeAndMessage(RBACError.EMAIL_VERIFICATION_ERROR);
        }

        Optional<UserRbac> user = userService.findOneByColumnName("email", email);
        if (user.isEmpty()) ItemFound.fail();

        UserRbac entity = user.get();
        entity.setEmailVerified(true);
        userService.save(entity);
        return Result.success();
    }

    private String checkVerification(String verification) {
        boolean status = true;
        //TODO
        return "XX@gmail.com";
    }
}
