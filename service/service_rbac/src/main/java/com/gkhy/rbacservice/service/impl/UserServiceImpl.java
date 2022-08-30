package com.gkhy.rbacservice.service.impl;

import com.gkhy.servicebase.user.IUserRepository;
import com.gkhy.servicebase.user.User;
import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.rbacservice.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends
        ServiceImpl<User, Long, IUserRepository>
        implements UserService {
    @Autowired
    public UserServiceImpl(IUserRepository iRepository) {
        super(iRepository);
    }

}
