package com.gkhy.rbacservice.service;


import com.gkhy.servicebase.user.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findOneByColumnName(String name, String username);
}
