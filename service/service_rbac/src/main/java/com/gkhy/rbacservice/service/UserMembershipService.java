package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.userDetails.UserMembership;

/**
 * @ClassName: UserMembershipService
 * @Description:
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
public interface UserMembershipService {
    UserMembership save(UserMembership userMembership);
}
