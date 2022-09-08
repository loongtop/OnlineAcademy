package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.permission.Resource;
import com.gkhy.rbacservice.repository.ResourceRepository;
import com.gkhy.rbacservice.service.ResourceService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Name: ResourceServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class ResourceServiceImpl
        extends ServiceImpl<Resource, ResourceRepository>
        implements ResourceService {
    public ResourceServiceImpl(ResourceRepository iRepository) {
        super(iRepository);
    }
}
