package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.group.Group;
import com.gkhy.rbacservice.repository.GroupRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: GroupController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/group")
public class GroupController extends ControllerBase<Group, GroupRepository> {
    public GroupController(GroupRepository groupRepository) {
        super(groupRepository);
    }
}
