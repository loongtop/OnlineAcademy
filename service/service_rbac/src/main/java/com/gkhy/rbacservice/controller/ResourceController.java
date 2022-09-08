package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.permission.Resource;
import com.gkhy.rbacservice.repository.ResourceRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: ResourceController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/

@RestController
@RequestMapping("/admin/resource")
public class ResourceController extends ControllerBase<Resource, ResourceRepository> {
    public ResourceController(ResourceRepository resourceRepository) {
        super(resourceRepository);
    }
}
