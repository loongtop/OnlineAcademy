package com.gkhy.rbacservice.repository;

import com.gkhy.servicebase.service.IRepositoryBase;
import com.gkhy.rbacservice.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends IRepositoryBase<Permission, Long> {
}
