package com.gkhy.rbacservice.service;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> queryAllMenu();

    void saveRolePermissionEntityRelationShip(Long roleId, Long[] permissionEntityId);

    List<Permission> selectAllMenu(Long roleId);
}
