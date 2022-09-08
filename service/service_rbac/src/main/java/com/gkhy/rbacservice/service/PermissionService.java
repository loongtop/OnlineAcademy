package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.permission.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    List<Permission> queryAllMenu();

    void saveRolePermissionEntityRelationShip(Long roleId, Long[] permissionEntityId);

    List<Permission> selectAllMenu(Long roleId);

    Permission save(Permission permission);

    Optional<Permission> findById(Long valueOf);

    List<Permission> findAll();

    List<Permission> findAllById(Iterable<Long> roleIds);

}
