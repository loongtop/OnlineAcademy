package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.repository.UserRepository;
import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.rbacservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends ServiceImpl<UserRbac, Long, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository iRepository) {
        super(iRepository);
    }
}
