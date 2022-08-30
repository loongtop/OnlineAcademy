package com.gkhy.rbacservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.service.PermissionService;
import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.rbacservice.entity.Permission;
import com.gkhy.rbacservice.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl
        extends ServiceImpl<Permission, Long, PermissionRepository>
        implements PermissionService {
    public PermissionServiceImpl(PermissionRepository iRepository) {
        super(iRepository);
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(Long id) {
        return null;
    }

    @Override
    public List<Long> selectPermissionValueByUserId(Long id) {
        return null;
    }

    @Override
    public List<Permission> queryAllMenu() {
        return null;
    }

    @Override
    public void removeChildById(Long id) {

    }

    @Override
    public void saveRolePermissionEntityRelationShip(Long roleId, Long[] permissionEntityId) {

    }

    @Override
    public List<Permission> selectAllMenu(Long roleId) {
        return null;
    }

    @Override
    public Permission save(Permission permissionEntity) {

        return permissionEntity;
    }
}
