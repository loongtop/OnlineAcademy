package com.gkhy.serviceoauth2.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.serviceoauth2.error.ErrorPage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController {
    @RequestMapping("/error/403")
    public Result error403() {
        return Result.fail().data("message", ErrorPage.ERROR_PAGE_403.getMessage());
    }
}
