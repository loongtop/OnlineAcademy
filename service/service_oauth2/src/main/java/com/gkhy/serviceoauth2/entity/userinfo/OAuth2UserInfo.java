package com.gkhy.serviceoauth2.entity.userinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo implements IOAuth2UserInfo {
    private Map<String, Object> attributes;

    public String getName() {
        return (String) this.getAttributes().get("name");
    }

    public String getEmail() {
        return (String) this.getAttributes().get("email");
    }

    public abstract String getId();
    public abstract String getImageUrl();
}
