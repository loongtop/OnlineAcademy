package com.gkhy.rbacservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.servicebase.user.User;
import com.gkhy.rbacservice.service.IndexService;
import com.gkhy.rbacservice.service.PermissionService;
import com.gkhy.rbacservice.service.RoleService;
import com.gkhy.rbacservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, Object> result = new HashMap<>();
        User user = userService.selectByUsername(username);
        if (null == user) {
            throw new AcademyException(11111,"");
        }


        List<Role> roleList = roleService.findRolesById(user.getId());
        List<String> roleNameList = roleList.stream().map(item -> item.getRoleName()).collect(Collectors.toList());
        if (roleNameList.size() == 0) {
            roleNameList.add("");
        }

        List<Long> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        redisService.set(username, permissionValueList);

        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    @Override
    public List<JSONObject> getMenu(String username) {
        User user = userService.selectByUsername(username);

        List<JSONObject> permissionList = permissionService.selectPermissionByUserId(user.getId());
        return permissionList;
    }

}
