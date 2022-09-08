package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.permission.Action;
import com.gkhy.rbacservice.repository.ActionRepository;
import com.gkhy.rbacservice.service.ActionService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Name: ActionServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class ActionServiceImpl
        extends ServiceImpl<Action, Long, ActionRepository>
        implements ActionService {
    public ActionServiceImpl(ActionRepository iRepository) {
        super(iRepository);
    }
}
