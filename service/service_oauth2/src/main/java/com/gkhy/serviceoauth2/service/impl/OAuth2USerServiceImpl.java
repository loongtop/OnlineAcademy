package com.gkhy.serviceoauth2.service.impl;

import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.servicebase.user.User;
import com.gkhy.serviceoauth2.entity.UserPrincipal;
import com.gkhy.servicebase.user.enums.AuthProvider;
import com.gkhy.serviceoauth2.entity.userinfo.OAuth2UserInfo;
import com.gkhy.serviceoauth2.entity.userinfo.OAuth2UserInfoFactory;
import com.gkhy.serviceoauth2.error.Oauth2Error;
import com.gkhy.serviceoauth2.service.OAuth2USerService;
import com.gkhy.serviceoauth2.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OAuth2USerServiceImpl extends DefaultOAuth2UserService implements OAuth2USerService {

    private final UserService userService;
    @Autowired
    public OAuth2USerServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public org.springframework.security.oauth2.core.user.OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        org.springframework.security.oauth2.core.user.OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private org.springframework.security.oauth2.core.user.OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, org.springframework.security.oauth2.core.user.OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new AcademyException(Oauth2Error.EMAIL_NOT_FOUND_FROM_OAUTH2.getCode(),
                    Oauth2Error.EMAIL_NOT_FOUND_FROM_OAUTH2.getMessage());
        }

        Optional<User> userOptional = userService.findByColumnName("email", oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new AcademyException(Oauth2Error.INVALIDED_USERNAME_PASSWORD.getCode(), "Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.of(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setUsername(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());

        return userService.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setUsername(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userService.save(existingUser);
    }

}
