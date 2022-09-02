package com.gkhy.rbacservice.entity.user;

import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @ClassName UserDetails
 * @Description: the details information of user
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/

@Setter
@Getter
@NoArgsConstructor
@Entity
public final class UserDetails extends DateModel {

    private static final long serialVersionUID = 142552555159746761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sign")
    private String sign;

    @Column(name = "mobile")
    private String mobile;

    private String imageUrl;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled = Boolean.TRUE;

    private String providerId;

    @Column(name = "birthdayDate")
    private java.time.LocalDate birthdayDate;
}
