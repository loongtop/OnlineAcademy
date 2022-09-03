package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.userDetails.UserDetails;
import com.gkhy.rbacservice.repository.UserDetailsRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserDetailsController
 * @Description:
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/userdetails")
public class UserDetailsController extends ControllerBase<UserDetails, Long, UserDetailsRepository> {

    public UserDetailsController(UserDetailsRepository userDetailsRepository) {
        super(userDetailsRepository);
    }

}
