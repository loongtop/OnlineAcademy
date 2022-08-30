package com.gkhy.eduorder;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan("com.gkhy")
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OrdersApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("OrdersApplication starting..........");
    }
}
