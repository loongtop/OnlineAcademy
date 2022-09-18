package com.gkhy.rbacservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexController {

    private final List<String> list = new ArrayList<>();

    private final IndexService indexService;
    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
        list.add("user1");
        list.add("user2");
        list.add("user3");
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
    @PostMapping("/layout")
    public Object layout(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", this.list);
        modelAndView.setViewName("/layout");
//        modelAndView.setViewName("redirect:/model/index1.html");
        return modelAndView;
    }

    @GetMapping("/test1")
    public Object test1(){
        return Result.success();
    }

    @NotControllerResponseAdvice
    @PostMapping("/test2")
    public Object test2(){
        //static file
        return"redirect:test1";
    }

    @NotControllerResponseAdvice
    @PostMapping("/test3")
    public Object test3(Model model){
        model.addAttribute("user", "leo");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:test1");
        return modelAndView;
    }

    @NotControllerResponseAdvice
    @PostMapping("/test4")
    public Object test4(Model model) {
        model.addAttribute("user", "leo");
        return "test";
    }
}
