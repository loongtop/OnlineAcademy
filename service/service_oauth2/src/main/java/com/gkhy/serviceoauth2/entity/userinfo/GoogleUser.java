package com.gkhy.serviceoauth2.entity.userinfo;

import java.util.Map;


public class GoogleUser extends OAuth2UserInfo {

    private static final long serialVersionUID = 3530985985516707500L;

    public GoogleUser(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) this.getAttributes().get("sub");
    }

    @Override
    public String getImageUrl() {
        return (String) this.getAttributes().get("picture");
    }
}
