package com.gkhy.rbacservice.service;


import com.gkhy.rbacservice.entity.UserRbac;

import java.util.Optional;

public interface UserService {

    Optional<UserRbac> findOneByColumnName(String name, String username);

    Optional<UserRbac> findById(Long id);

    UserRbac save(UserRbac entity);

    boolean existsByColumnName(String email, String email1);
}
