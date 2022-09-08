package com.gkhy.rbacservice.repository;

import com.gkhy.rbacservice.entity.permission.Resource;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

/**
 * @Name: ResourceRepository
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Repository
public interface ResourceRepository extends IRepositoryBase<Resource> {
}
