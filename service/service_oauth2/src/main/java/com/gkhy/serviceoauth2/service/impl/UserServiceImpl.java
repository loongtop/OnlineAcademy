package com.gkhy.serviceoauth2.service.impl;

import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.servicebase.service.ServiceImpl;
import com.gkhy.servicebase.user.IUserRepository;
import com.gkhy.servicebase.user.User;
import com.gkhy.servicebase.utils.ItemFound;
import com.gkhy.serviceoauth2.entity.UserPrincipal;
import com.gkhy.serviceoauth2.error.Oauth2Error;
import com.gkhy.serviceoauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl
        extends ServiceImpl<User, Long, IUserRepository>
        implements UserService {

    @Autowired
    public UserServiceImpl(IUserRepository iRepository) {
        super(iRepository);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = this.findOneByColumnName("email", email);
        if (user.isEmpty()) {
            throw new AcademyException(Oauth2Error.EMAIL_NOT_FOUND_FROM_DATABASE.getCode(),
                    Oauth2Error.EMAIL_NOT_FOUND_FROM_DATABASE.getMessage() + email);
        }

        return UserPrincipal.of(user.get());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Optional<User> user = this.findById(id);
        if (user.isEmpty()) {
            throw new AcademyException(ItemFound.fail().getCode(),
                    ItemFound.fail().getMessage());
        }

        return UserPrincipal.of(user.get());
    }



}