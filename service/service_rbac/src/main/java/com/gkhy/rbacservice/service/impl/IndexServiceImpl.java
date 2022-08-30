package com.gkhy.rbacservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.user.User;
import com.gkhy.rbacservice.service.IndexService;
import com.gkhy.rbacservice.service.PermissionService;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.rbacservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexServiceImpl implements IndexService {

    private final UserService userService;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final RedisService redisService;

    @Autowired
    public IndexServiceImpl(UserService userService, RoleService roleService, PermissionService permissionService, RedisService redisTemplate) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.redisService = redisTemplate;
    }


    @Override
    public Map<String, Object> getUserInfo(String username) {

        Optional<User> optionalUser = userService.findOneByColumnName("user_name", username);
        User user = optionalUser
                .orElseThrow(()->new NoSuchElementException(String.format("Can not find User bu name %s", username)));

//        List<Role> roleList = roleService.findById(user.getId()));
//
//        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
//        if (roleNameList.size() == 0) {
//            roleNameList.add("");
//        }
//
//        List<Long> permissionValueList = permissionService.findById(user.getId());
//        redisService.set(username, permissionValueList);

//        Map<String, Object> result = new HashMap<>();
//        result.put("name", user.getUsername());
//        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        result.put("roles", roleNameList);
//        result.put("permissionValueList", permissionValueList);
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public List<JSONObject> getMenu(String username) {
        return null;
    }

}
