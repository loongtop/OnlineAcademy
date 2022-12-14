package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.service.PermissionService;
import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.rbacservice.entity.permission.Permission;
import com.gkhy.rbacservice.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Name: PermissionServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class PermissionServiceImpl
        extends ServiceImpl<Permission, Long, PermissionRepository> implements PermissionService {

    public PermissionServiceImpl(PermissionRepository iRepository) {
        super(iRepository);
    }

    @Override
    public List<Permission> queryAllMenu() {
        return null;
    }

    @Override
    public void saveRolePermissionEntityRelationShip(Long roleId, Long[] permissionEntityId) {

    }

    @Override
    public List<Permission> selectAllMenu(Long roleId) {
        return null;
    }


}
