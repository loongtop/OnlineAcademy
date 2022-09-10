package com.gkhy.rbacservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.rbacservice.url.Url;
import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class IndexController {

    private List<String> list = new ArrayList<>();

    private final IndexService indexService;
    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
        list.add("user1");
        list.add("user2");
    }

    @GetMapping("/info")
    public Result info(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.success().data(userInfo);
    }

    @GetMapping("/menu")
    public Result getMenu(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Result.success().data("permissionList", permissionList);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.success();
    }

    @NotControllerResponseAdvice
    @PostMapping("/text3")
    public Object text3(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.list);
        modelAndView.setViewName("redirect:/model/index1.html");
        return modelAndView;
    }

    @NotControllerResponseAdvice
    @PostMapping("/text4")
    public Object text4(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.list);
        modelAndView.setViewName("index2");
        return modelAndView;
    }

}
