package com.gkhy.eduservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

//    private AppUserRepository appUserRepository;

    private static final Logger logger = LoggerFactory.getLogger(BootStrapData.class);

    @Override
    public void run(String... args) {
//        AppUserDetailsImpl appUserDetailsImpl = new AppUserDetailsImpl();
//
//        appUserRepository.save(appUserDetailsImpl);
    }
}
