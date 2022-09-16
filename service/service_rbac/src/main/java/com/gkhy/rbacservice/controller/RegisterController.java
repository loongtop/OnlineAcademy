package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.request.RegisterRequest;
import com.gkhy.rbacservice.service.RegisterService;
import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import com.gkhy.servicebase.utils.ResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    private final RegisterService registerService;
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    //register a user
    @NotControllerResponseAdvice
    @PostMapping(path = "/register")
    public Object register(@Valid RegisterRequest registerRequest) {
        ModelAndView responseModel = ResponseModel.of("redirect:login");

        return registerService.register(registerRequest, responseModel);
    }

}
