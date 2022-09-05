package com.gkhy.rbacservice.entity.userDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.rbacservice.entity.enums.Gender;
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
@JsonIgnoreProperties(value = "{user}")
@Entity
public final class UserDetails extends DateModel {

    private static final long serialVersionUID = 142552555159746761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sign")
    private String sign;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled = Boolean.TRUE;

    @Column(name = "birthdayDate")
    private java.time.LocalDate birthdayDate;

    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private UserRbac user;

}
