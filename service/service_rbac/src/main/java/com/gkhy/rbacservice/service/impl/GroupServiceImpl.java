package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.group.Group;
import com.gkhy.rbacservice.repository.GroupRepository;
import com.gkhy.rbacservice.service.GroupService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Name: GroupServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class GroupServiceImpl
        extends ServiceImpl<Group, GroupRepository>
        implements GroupService {
    public GroupServiceImpl(GroupRepository iRepository) {
        super(iRepository);
    }
}
