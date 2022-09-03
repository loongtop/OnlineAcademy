package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.userDetails.UserMembership;
import com.gkhy.rbacservice.repository.UserMembershipRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserMembershipController
 * @Description:
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/usermembership")
public class UserMembershipController extends ControllerBase<UserMembership, Long, UserMembershipRepository> {

    public UserMembershipController(UserMembershipRepository userMembershipRepository) {
        super(userMembershipRepository);
    }

}
