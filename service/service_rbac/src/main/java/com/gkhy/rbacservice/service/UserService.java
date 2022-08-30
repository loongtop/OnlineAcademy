package com.gkhy.rbacservice.service;

import com.gkhy.servicebase.user.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void removeById(Long id);

    void removeByIds(List<Long> idList);

    User selectByUsername(String username);
}
