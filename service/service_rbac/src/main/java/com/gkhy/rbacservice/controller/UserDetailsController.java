package com.gkhy.rbacservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.userDetails.UserDetails;
import com.gkhy.rbacservice.repository.UserDetailsRepository;
import com.gkhy.rbacservice.service.UserDetailsService;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsRepository userDetailsRepository, UserService userService, UserDetailsService userDetailsService) {
        super(userDetailsRepository);
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/add/{id}")
    public Result add(@Valid @PathVariable Long id, @Valid @RequestBody JSONObject obj) {
        Optional<UserRbac> user = userService.findById(id);
        if (user.isEmpty()) ItemFound.fail().data("message", "Can not find Group in the database!");

        UserDetails userDetails = this.JSONObjectToT(obj);
        userDetails.setUser(user.get());
        userDetails = userDetailsService.save(userDetails);

        return Result.success().data("item", userDetails);
    }
}
