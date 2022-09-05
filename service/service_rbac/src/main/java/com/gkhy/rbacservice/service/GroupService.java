package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.group.Group;

import java.util.Optional;

/**
 * @Name: GroupService
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
public interface GroupService {
    Group save(Group group);

    Optional<Group> findById(Long id);
}
