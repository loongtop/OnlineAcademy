package com.gkhy.serviceoauth2.entity.userinfo;

import java.util.Map;

public class GithubUser extends OAuth2UserInfo {

    private static final long serialVersionUID = -9169987484979729965L;

    public GithubUser(Map<String, Object> attributes) {
        super(attributes);
    }
    @Override
    public String getId() {
        return (this.getAttributes().get("id")).toString();
    }

    @Override
    public String getImageUrl() {
        return (String) this.getAttributes().get("avatar_url");
    }
}
