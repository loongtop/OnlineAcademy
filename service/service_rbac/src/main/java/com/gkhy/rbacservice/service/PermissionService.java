package com.gkhy.rbacservice.service;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> queryAllMenu();

    void removeChildById(Long id);

    void saveRolePermissionEntityRelationShip(Long roleId, Long[] permissionEntityId);

    List<Permission> selectAllMenu(Long roleId);

    Permission save(Permission permissionEntity);

    List<JSONObject> selectPermissionByUserId(Long id);

    List<Long> selectPermissionValueByUserId(Long id);
}
