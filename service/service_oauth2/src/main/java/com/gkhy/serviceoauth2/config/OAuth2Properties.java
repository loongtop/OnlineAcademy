package com.gkhy.serviceoauth2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigurationProperties(prefix = "app")
public class OAuth2Properties {

    private final TokenParam tokenParam;
    private final OAuth2 oauth2 = new OAuth2();
    @Autowired
    public OAuth2Properties(TokenParam tokenParam) {
        this.tokenParam = tokenParam;
    }

    @Getter
    @Setter
    @Component
    public static class TokenParam {
//        @Value("${app.auth.tokenSecret}")
        private String tokenSecret;
//        @Value("${app.auth.tokenExpirationMsec}")
        private long tokenExpirationMsec;
    }

    @Getter
    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
