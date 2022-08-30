package com.gkhy.serviceoauth2.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.gkhy.servicebase.user.User;

@Builder
@Getter
public class UserPrincipal implements org.springframework.security.oauth2.core.user.OAuth2User, UserDetails {

    private static final long serialVersionUID = -297577192014821692L;

    private final Long id;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private  Map<String, Object> attributes;

    public static UserPrincipal of(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return UserPrincipal.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

    public static UserPrincipal of(User user, Map<String, Object> attributes) {
        UserPrincipal userFactory = UserPrincipal.of(user);
        userFactory.setAttributes(attributes);
        return userFactory;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
