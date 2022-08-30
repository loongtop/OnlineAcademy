package com.gkhy.serviceoauth2.entity.userinfo;

import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.servicebase.user.enums.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        if (AuthProvider.GOOGLE.name().equalsIgnoreCase(registrationId)) {
            return new GoogleUser(attributes);
        } else if (AuthProvider.FACEBOOK.name().equalsIgnoreCase(registrationId)) {
            return new FacebookUser(attributes);
        } else if (AuthProvider.GITHUB.name().equalsIgnoreCase(registrationId)) {
            return new GithubUser(attributes);
        } else {
            throw new AcademyException(20014 ,"Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
