package com.gkhy.rbacservice.service;


import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.group.Group;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<UserRbac> findOneByColumnName(String name, String username);

    Optional<UserRbac> findById(Long id);

    UserRbac save(UserRbac entity);

    boolean existsByColumnName(String email, String email1);

    List<UserRbac> findAll();

    Set<Role> getRoles(UserRbac entity);

    Set<Group> getGroups(UserRbac userRbac);
}
