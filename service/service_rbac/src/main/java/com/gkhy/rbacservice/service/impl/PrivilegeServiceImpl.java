package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.group.Group;
import com.gkhy.rbacservice.entity.privilege.Privilege;
import com.gkhy.rbacservice.repository.GroupRepository;
import com.gkhy.rbacservice.repository.PrivilegeRepository;
import com.gkhy.rbacservice.service.PrivilegeService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Name: PrivilegeServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class PrivilegeServiceImpl
        extends ServiceImpl<Privilege, Long, PrivilegeRepository>
        implements PrivilegeService {
    public PrivilegeServiceImpl(PrivilegeRepository iRepository) {
        super(iRepository);
    }
}
