package com.gkhy.cmsservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gkhy"})
public class CmsApplication {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication app = new SpringApplication(CmsApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        System.out.println("CmsApplication starting..........");
    }
}
