package com.gkhy.msmservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.gkhy")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MsmApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("MsmApplication starting..........");
    }
}
