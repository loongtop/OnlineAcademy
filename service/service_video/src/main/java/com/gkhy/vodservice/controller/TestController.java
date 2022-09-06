package com.gkhy.vodservice.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.vodservice.Permission;
import com.gkhy.vodservice.Privilege;
import com.gkhy.vodservice.service.PermissionService;
import com.gkhy.vodservice.service.PrivilegeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @Name: TestController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-05
 * @Updated: 2022-09-05
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    private final PermissionService permissionService;
    private final PrivilegeService privilegeService;

    public TestController(PermissionService permissionService, PrivilegeService privilegeService) {
        this.permissionService = permissionService;
        this.privilegeService = privilegeService;
    }

    @PostMapping("/all")
    public Result testAll() {
        List<Permission> permissions = permissionService.findAll();
//        List<Privilege> privileges = privilegeService.findAll();
        System.out.println("test-all");
        return Result.success()
                .data("permission",permissions);
//                .data("privileges", privileges);
    }

    @PostMapping("/permission")
    public Result testPermission() {
        Permission permissions = permissionService.findByName("permission4");
        System.out.println("test-permission");
        return Result.success().data("permission",permissions);
    }

    @PostMapping("/privilege")
    public Result testPrivilege() {
        Privilege privilege = privilegeService.findByName("p01=>action1-resource1");
        System.out.println("test-privilege");
        return Result.success().data("privilege",privilege);
    }

}
