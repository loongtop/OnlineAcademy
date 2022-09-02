package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.repository.RoleRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/rbac/role")
public class RoleController extends ControllerBase<Role, Long, RoleRepository> {

    private final RoleService roleService;
    @Autowired
    public RoleController(RoleRepository roleRepository, RoleService roleService) {
        super(roleRepository);
        this.roleService = roleService;
    }



}
