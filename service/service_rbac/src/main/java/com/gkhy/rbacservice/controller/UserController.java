package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.request.RegisterRequest;
import com.gkhy.rbacservice.repository.UserRepository;
import com.gkhy.rbacservice.service.RegisterService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.rbacservice.service.UserService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.servicebase.result.status.StatusCode;
import com.gkhy.servicebase.utils.ItemFound;
import com.gkhy.servicebase.utils.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 * UserController
 * </p>
 *
 * @author leo
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/admin/user")
@Validated
public class UserController extends ControllerBase<UserRbac, Long, UserRepository> {

    private final UserService userService;
    private final RoleService roleService;
    private final RegisterService registerService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService, RoleService roleService, RegisterService registerService) {
        super(userRepository);
        this.userService = userService;
        this.roleService = roleService;
        this.registerService = registerService;
    }

    @PostMapping("/addRoles/{id}")
    public Result addRoles(@PathVariable @Min(1) Long id, @RequestParam("ids") @NotNull List<Long> ids) {
        Optional<UserRbac> user = userService.findById(id);
        if (user.isEmpty()) Result.fail().codeAndMessage(StatusCode.ITEM_NOT_FOUND_ERROR);

        List<Role> roleList = roleService.findAllById(ids);

        if (roleList.size() <= 0) return ItemFound.fail();

        UserRbac entity = user.get();
        roleList.forEach(r->{
            entity.getRoles().add(r);
            r.getUser().add(entity);
            roleService.save(r);
        });

        userService.save(entity);

        return Result.success().data("item", entity);
    }

    @DeleteMapping("/removeRoles/{id}")
    public Result removeRoles(@PathVariable @Min(1) Long id, @RequestParam("ids") @NotNull List<Long> ids) {
        Optional<UserRbac> user = userService.findById(id);
        if (user.isEmpty()) return ItemFound.fail();

        UserRbac entity = user.get();
        Set<Role> roleSet = entity.getRoles();
        List<Role> roleList = roleService.findAllById(ids);

        if (roleList.size() <= 0) return ItemFound.fail();

        roleList.forEach(role -> {
            role.getUser().remove(entity);
            roleService.save(role);
            roleSet.remove(role);
        });

        userService.save(entity);
        return Result.success().data("item", entity);
    }

    @GetMapping("/getAdd")
    public Object getAdd(HttpServletRequest httpServletRequest) {
        return new ModelAndView("admin/user/user-add");
    }

    @PostMapping("/add")
    public Object add(@Valid RegisterRequest registerRequest) {
        ModelAndView modelAndView = ResponseModel.of("/all");
        return registerService.register(registerRequest, modelAndView);
    }
}
