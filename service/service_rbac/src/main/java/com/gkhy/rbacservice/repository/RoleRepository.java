package com.gkhy.rbacservice.repository;

import com.gkhy.servicebase.service.IRepositoryBase;
import com.gkhy.rbacservice.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends IRepositoryBase<Role, Long> {
}
