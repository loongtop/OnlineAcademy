package com.gkhy.rbacservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.userDetails.UserDetails;
import com.gkhy.rbacservice.entity.userDetails.UserMembership;
import com.gkhy.rbacservice.repository.UserMembershipRepository;
import com.gkhy.rbacservice.service.UserDetailsService;
import com.gkhy.rbacservice.service.UserMembershipService;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    private final UserService userService;
    private final UserMembershipService userMembershipService;
    public UserMembershipController(UserMembershipRepository userMembershipRepository, UserService userService, UserMembershipService userMembershipService) {
        super(userMembershipRepository);
        this.userService = userService;
        this.userMembershipService = userMembershipService;
    }

    @PostMapping("/add/{id}")
    public Result add(@Valid @PathVariable Long id, @Valid @RequestBody JSONObject obj) {
        Optional<UserRbac> user = userService.findById(id);
        if (user.isEmpty()) ItemFound.fail().data("message", "Can not find Group in the database!");

        UserMembership userMembership = this.JSONObjectToT(obj);
        userMembership.setUser(user.get());
        userMembership = userMembershipService.save(userMembership);

        return Result.success().data("item", userMembership);
    }

}
