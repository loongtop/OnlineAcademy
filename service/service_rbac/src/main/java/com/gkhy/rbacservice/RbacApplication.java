package com.gkhy.rbacservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "com.gkhy")
public class RbacApplication {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication app = new SpringApplication(RbacApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("RbacApplication starting..........");
    }
}
