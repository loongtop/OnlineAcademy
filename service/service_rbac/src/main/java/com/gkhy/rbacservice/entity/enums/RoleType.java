package com.gkhy.rbacservice.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    ROLE_ADMIN("0001"),
    ROLE_CLIENT("0002"),
    ;

    private final String code;

    RoleType(String code) {
        this.code = code;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
