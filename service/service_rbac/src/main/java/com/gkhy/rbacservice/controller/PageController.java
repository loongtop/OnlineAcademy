package com.gkhy.rbacservice.controller;

import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Name: PageController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-09
 * @Updated: 2022-09-09
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/to")
public class PageController {

    @NotControllerResponseAdvice
    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page) {
        return page;
    }
}
