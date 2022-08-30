package com.gkhy.servicebase.config;

//import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//Configuration class annotations
@EnableSwagger2 //Swagger2 annotations
public class SwaggerConfig {
    //http://localhost:8081/swagger-ui.html

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gkhy."))
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.ant("/admin/.*"))
                .paths(PathSelectors.ant("/error.*"))
//                .paths(Predicates.not(PathSelectors.any()))
                .build();
    }

    private ApiInfo webApiInfo(){
        String url = "http://online.education.com";
        String email = "loongtop@gmail.com";
        return new ApiInfoBuilder()
                .title("Website - Course Center API Documentation")
                .description("This document describes the course center microservice interface definition")
                .version("1.0")
                .contact(new Contact("java", url, email))
                .build();
    }
}
