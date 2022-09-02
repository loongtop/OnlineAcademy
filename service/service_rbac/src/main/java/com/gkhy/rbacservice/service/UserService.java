package com.gkhy.rbacservice.service;


import com.gkhy.rbacservice.entity.RbacUser;

import java.util.Optional;

public interface UserService {

    Optional<RbacUser> findOneByColumnName(String name, String username);

    Optional<RbacUser> findById(Long id);

    void save(RbacUser entity);
}
