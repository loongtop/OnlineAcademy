package com.gkhy.rbacservice.service.impl;

import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.repository.RoleRepository;
import com.gkhy.rbacservice.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @Name: RoleServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class RoleServiceImpl
        extends ServiceImpl<Role, RoleRepository> implements RoleService {

    public RoleServiceImpl(RoleRepository iRepository) {
        super(iRepository);
    }

    @Override
    public void saveUserRoleRelationShip(Long userId, Long[] roleId) {
    }


}
