package com.gkhy.vodservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gkhy"})
@EnableDiscoveryClient
public class VodApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(VodApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("VodApplication starting..........");
    }
}

