package com.gkhy.rbacservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.gkhy.rbacservice.entity.group.Group;
import com.gkhy.rbacservice.entity.group.GroupDetails;
import com.gkhy.rbacservice.repository.GroupDetailsRepository;
import com.gkhy.rbacservice.service.GroupDetailsService;
import com.gkhy.rbacservice.service.GroupService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @Name: GroupDetailsController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/admin/groupdetails")
public class GroupDetailsController extends ControllerBase<GroupDetails, Long, GroupDetailsRepository> {

    private final GroupService groupService;
    private final GroupDetailsService groupDetailsService;

    public GroupDetailsController(GroupDetailsRepository groupDetailsRepository, GroupService groupService, GroupDetailsService groupDetailsService) {
        super(groupDetailsRepository);
        this.groupService = groupService;
        this.groupDetailsService = groupDetailsService;
    }

    @PostMapping("/add/{id}")
    public Result add(@PathVariable @Min(1) Long id, @RequestBody @NotNull JSONObject obj) {
        Optional<Group> group = groupService.findById(id);
        if (group.isEmpty()) return ItemFound.fail();

        GroupDetails groupDetails = this.JSONObjectToT(obj);
        groupDetails.setGroup(group.get());
        groupDetails = groupDetailsService.save(groupDetails);

        return Result.success().data("item", groupDetails);
    }
}
