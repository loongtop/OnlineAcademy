package com.gkhy.servicebase.user;

import com.gkhy.servicebase.basemodel.DateModel;
import com.gkhy.servicebase.user.enums.AuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance
@Table(name = "member_center",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
public class User extends DateModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

    @Column(name = "USER_NAME", nullable = false)
    protected String username;

    @Column(name = "PASSWORD")
    protected String password;

    @Email
    @Column(name = "EMAIL", nullable = false)
    protected String email;

    @Column(name = "GENDER")
    protected Integer gender;

    @Column(name = "AGE")
    protected Integer age;

    @Column(name = "SIGN")
    protected String sign;

    @Column(name = "MOBILE")
    protected String mobile;

    @Column(nullable = false)
    protected Boolean emailVerified = false;

    protected String imageUrl;

    @Column(name = "ACCOUNT_EXPIRED")
    protected boolean accountNonExpired;

    @Column(name = "ACCOUNT_LOCKED")
    protected boolean accountNonLocked;

    @Column(name = "CREDENTIALS_EXPIRED")
    protected boolean credentialsNonExpired;

    @Column(name = "ENABLED")
    protected Boolean enabled = Boolean.TRUE;

    protected String providerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
}
