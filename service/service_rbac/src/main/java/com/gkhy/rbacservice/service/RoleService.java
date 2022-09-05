package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAllByColumnName(String roleName, String test1);

    Optional<Role> findOneByColumnName(String roleName, String test1);

    Optional<Role> findById(Long userId);

    void saveUserRoleRelationShip(Long userId, Long[] roleId);

    Role save(Role role);

    List<Role> findAllById(Iterable<Long> roleIds);
}
