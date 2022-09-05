package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.privilege.Privilege;

import java.util.List;
import java.util.Optional;

/**
 * @Name: PrivilegeService
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
public interface PrivilegeService {
    Privilege save(Privilege privilege);

    List<Privilege> findAll();

    Optional<Privilege> findById(Long valueOf);
}
