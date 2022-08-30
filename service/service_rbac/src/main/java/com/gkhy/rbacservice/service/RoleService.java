package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void saveUserRoleRelationShip(Long userId, Long[] roleId);

    Optional<Role> findById(Long userId);

    Role save(Role role);

    Role update(Role role);

    void removeById(Long id);

    void removeByIds(List<Long> idList);

    List<Role> findRolesById(Long id);

    long count();
}
