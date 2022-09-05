package com.gkhy.rbacservice.service.impl;

import com.gkhy.rbacservice.entity.group.GroupDetails;
import com.gkhy.rbacservice.repository.GroupDetailsRepository;
import com.gkhy.rbacservice.service.GroupDetailsService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Name: GroupDetailsServiceImpl
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Service
public class GroupDetailsServiceImpl
        extends ServiceImpl<GroupDetails, Long, GroupDetailsRepository>
        implements GroupDetailsService {
    public GroupDetailsServiceImpl(GroupDetailsRepository iRepository) {
        super(iRepository);
    }
}
