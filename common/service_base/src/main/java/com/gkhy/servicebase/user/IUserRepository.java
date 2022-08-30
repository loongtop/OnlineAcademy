package com.gkhy.servicebase.user;

import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IRepositoryBase<User, Long> {
}
