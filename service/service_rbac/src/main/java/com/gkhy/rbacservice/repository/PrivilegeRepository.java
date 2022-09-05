package com.gkhy.rbacservice.repository;

import com.gkhy.rbacservice.entity.privilege.Privilege;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

/**
 * @Name: PrivilegeRepository
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Repository
public interface PrivilegeRepository extends IRepositoryBase<Privilege, Long> {
}
