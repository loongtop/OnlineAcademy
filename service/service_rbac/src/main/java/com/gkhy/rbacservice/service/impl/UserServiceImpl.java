package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.group.Group;
import com.gkhy.rbacservice.repository.UserRepository;
import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.rbacservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Name: UserServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl
        extends ServiceImpl<UserRbac, Long, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository iRepository) {
        super(iRepository);
    }

    @Override
    public Set<Role> getRoles(UserRbac userRbac) {
        return userRbac.getRoles();
    }

    @Override
    public Set<Group> getGroups(UserRbac userRbac) {
        return userRbac.getGroups();
    }
}
