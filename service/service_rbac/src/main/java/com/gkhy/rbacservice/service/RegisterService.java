package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.request.RegisterRequest;
import com.gkhy.servicebase.result.Result;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Name: RegisterService
 * @Description:
 * @Author: leo
 * @Created: 2022-09-16
 * @Updated: 2022-09-16
 * @Version: 1.0
 **/
public interface RegisterService {
    Object register(RegisterRequest registerRequest, ModelAndView responseModel);

    Result checkTwoPasswords(String password1, String password2);
}
