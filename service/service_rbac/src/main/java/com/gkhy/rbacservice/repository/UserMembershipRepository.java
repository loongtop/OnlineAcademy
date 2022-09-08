package com.gkhy.rbacservice.repository;


import com.gkhy.rbacservice.entity.userDetails.UserMembership;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserMembershipRepository
 * @Description:
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
@Repository
public interface UserMembershipRepository extends IRepositoryBase<UserMembership> {
}
