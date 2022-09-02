package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.user.UserDetails;
import com.gkhy.rbacservice.repository.UserDetailsRepository;
import com.gkhy.rbacservice.service.UserDetailsService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserDetailsServiceImpl
 * @Description:
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
@Service
public class UserDetailsServiceImpl
        extends ServiceImpl<UserDetails, Long, UserDetailsRepository> implements UserDetailsService {

    public UserDetailsServiceImpl(UserDetailsRepository iRepository) {
        super(iRepository);
    }
}
