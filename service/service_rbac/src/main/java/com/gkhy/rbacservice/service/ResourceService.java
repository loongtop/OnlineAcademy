package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.privilege.Resource;

import java.util.List;
import java.util.Optional;

/**
 * @Name: ResourceService
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
public interface ResourceService {
    Resource save(Resource resource);

    List<Resource> findAll();

    Optional<Resource> findById(Long i);
}
