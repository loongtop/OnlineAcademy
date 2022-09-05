package com.gkhy.rbacservice.service;

import com.gkhy.rbacservice.entity.privilege.Action;

import java.util.List;

/**
 * @Name: ActionService
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
public interface ActionService {
    Action save(Action action);

    List<Action> findAll();
}
