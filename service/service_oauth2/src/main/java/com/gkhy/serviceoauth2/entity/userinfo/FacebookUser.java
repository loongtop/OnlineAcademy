package com.gkhy.serviceoauth2.entity.userinfo;

import java.util.Map;

public final class FacebookUser extends OAuth2UserInfo {
    private static final long serialVersionUID = -8311379045730218853L;

    public FacebookUser(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) this.getAttributes().get("id");
    }

    @Override
    public String getImageUrl() {
        if(this.getAttributes().containsKey("picture")) {
            Map<String, Object> pictureObj = (Map<String, Object>) this.getAttributes().get("picture");
            if(pictureObj.containsKey("data")) {
                Map<String, Object>  dataObj = (Map<String, Object>) pictureObj.get("data");
                if(dataObj.containsKey("url")) {
                    return (String) dataObj.get("url");
                }
            }
        }
        return null;
    }
}
