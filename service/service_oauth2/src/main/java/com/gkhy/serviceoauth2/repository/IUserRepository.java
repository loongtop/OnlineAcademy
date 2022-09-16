package com.gkhy.serviceoauth2.repository;

import com.gkhy.servicebase.service.IRepositoryBase;
import com.gkhy.serviceoauth2.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends IRepositoryBase<User, Long> {
}
