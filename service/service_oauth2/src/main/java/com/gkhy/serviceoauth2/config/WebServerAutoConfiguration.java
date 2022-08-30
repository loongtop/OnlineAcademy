package com.gkhy.serviceoauth2.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class WebServerAutoConfiguration {
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");

        factory.addErrorPages(errorPage400);
        return factory;
    }
}
