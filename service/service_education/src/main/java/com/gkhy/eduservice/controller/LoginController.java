package com.gkhy.eduservice.controller;

import com.gkhy.servicebase.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class LoginController {
    //login
    @PostMapping("login")
    public Result login() {
        return Result.success().data("token","admin-cy");
    }
    //info
    @GetMapping("info")
    public Result info() {
        return Result.success()
                .data("name","admin_info")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.success()
                .data("name","admin_logout")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
