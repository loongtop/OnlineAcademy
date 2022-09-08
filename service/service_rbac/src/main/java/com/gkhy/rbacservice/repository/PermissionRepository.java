package com.gkhy.rbacservice.repository;

import com.gkhy.servicebase.service.IRepositoryBase;
import com.gkhy.rbacservice.entity.permission.Permission;
import org.springframework.stereotype.Repository;
/**
 * @Name: PermissionRepository
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Repository
public interface PermissionRepository extends IRepositoryBase<Permission, Long> {
}
