package com.gkhy.ossservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        MultipartAutoConfiguration.class,
        WebMvcAutoConfiguration.class})
@ComponentScan(basePackages = {"com.gkhy"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OssApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("OssApplication starting..........");
    }
}

