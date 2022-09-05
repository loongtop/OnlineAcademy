package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.userDetails.UserDetails;

/**
 * @ClassName: UserDetailsService
 * @Description:
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
public interface UserDetailsService {
    UserDetails save(UserDetails userDetails);
}
