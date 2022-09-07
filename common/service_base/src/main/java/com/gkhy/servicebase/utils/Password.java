package com.gkhy.servicebase.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Name: Password
 * @Description:
 * @Author: leo
 * @Created: 2022-09-06
 * @Updated: 2022-09-06
 * @Version: 1.0
 **/
@Component
public class Password {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
