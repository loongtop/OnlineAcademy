package com.gkhy.serviceoauth2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gkhy.servicebase.basemodel.DateModel;
import com.gkhy.serviceoauth2.entity.enums.AuthProvider;
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
@JsonIgnoreProperties(value = "{password}")
@Table(name = "member_center",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
public class User extends DateModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

    @Column(name = "USER_NAME", nullable = false)
    protected String username;

    @JsonIgnore
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

    @Column(name = "BIRTHDATE")
    private java.time.LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Column(name = "NUMBER")
    private Long number;
}
