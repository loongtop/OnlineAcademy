package com.gkhy.serviceoauth2;

import com.gkhy.serviceoauth2.config.OAuth2Properties;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = "com.gkhy")
@SpringBootApplication
@EnableConfigurationProperties(OAuth2Properties.class)
public class Oauth2Application {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication app = new SpringApplication(Oauth2Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("Oauth2Application starting..........");
    }
}
