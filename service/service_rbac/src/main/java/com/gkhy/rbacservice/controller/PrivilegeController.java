package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.privilege.Privilege;
import com.gkhy.rbacservice.repository.PrivilegeRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: PrivilegeController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/privilege")
public class PrivilegeController extends ControllerBase<Privilege, Long, PrivilegeRepository> {
    public PrivilegeController(PrivilegeRepository privilegeRepository) {
        super(privilegeRepository);
    }
}
