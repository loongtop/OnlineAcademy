package com.gkhy.serviceoauth2.service;

import com.gkhy.serviceoauth2.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService
        extends org.springframework.security.core.userdetails.UserDetailsService{

    boolean existsByColumnName(String email, String email1);

    User save(User user);

    Optional<User> findById(Long valueOf);

    Optional<User> findOneByColumnName(String email, String email1);

    UserDetails loadUserById(Long userId);
}