package com.gkhy.rbacservice.repository;

import com.gkhy.rbacservice.entity.privilege.Action;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

/**
 * @Name: ActionRepository
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Repository
public interface ActionRepository extends IRepositoryBase<Action, Long> {
}
