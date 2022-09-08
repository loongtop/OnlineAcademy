package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.userDetails.UserMembership;
import com.gkhy.rbacservice.repository.UserMembershipRepository;
import com.gkhy.rbacservice.service.UserMembershipService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserMembershipServiceImpl
 * @Description:
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
@Service
public class UserMembershipServiceImpl
        extends ServiceImpl<UserMembership, UserMembershipRepository> implements UserMembershipService {

    public UserMembershipServiceImpl(UserMembershipRepository iRepository) {
        super(iRepository);
    }
}
