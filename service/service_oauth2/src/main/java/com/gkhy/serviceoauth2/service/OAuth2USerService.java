package com.gkhy.serviceoauth2.service;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuth2USerService {
    
    OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest);

}
