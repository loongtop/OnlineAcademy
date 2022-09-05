package com.gkhy.rbacservice.controller;

import com.gkhy.rbacservice.entity.privilege.Action;
import com.gkhy.rbacservice.repository.ActionRepository;
import com.gkhy.servicebase.controller.ControllerBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Name: ActionController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/action")
public class ActionController  extends ControllerBase<Action, Long, ActionRepository> {
    public ActionController(ActionRepository actionRepository) {
        super(actionRepository);
    }

}
