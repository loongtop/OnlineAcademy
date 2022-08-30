package com.gkhy.serviceoauth2.entity.userinfo;

import java.io.Serializable;

public interface IOAuth2UserInfo extends Serializable {
    String getName();
    String getEmail();
    String getId();
    String getImageUrl();
}
