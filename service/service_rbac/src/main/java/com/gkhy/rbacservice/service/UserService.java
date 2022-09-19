package com.gkhy.rbacservice.service;


import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.group.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<UserRbac> findOneByColumnName(String name, String username);

    Optional<UserRbac> findById(Long id);

    UserRbac save(UserRbac entity);

    boolean existsByColumnName(String email, String email1);

    Set<Role> getRoles(UserRbac entity);

    Set<Group> getGroups(UserRbac userRbac);

    Page<UserRbac> findAll(Specification<UserRbac> spec, Pageable pageable);

    List<UserRbac> findAll();
}
