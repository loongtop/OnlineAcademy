package com.gkhy.rbacservice.entity;

import com.gkhy.rbacservice.entity.permission.Privilege;
import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName Permission
 * @Description: the permission of the users
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public final class Permission extends DateModel {

    private static final long serialVersionUID = -4961118546104218207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private Privilege privilege;

    private Integer type;

    private String permissionValue;

    private String url;

    private String component;

    private String icon;

    private Integer status;

    private Integer level;

    private boolean isSelect;

    private Boolean enabled = Boolean.TRUE;

    private LocalDateTime expiryTime;

    @Transient
    private List<?> permissions;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(targetEntity = Privilege.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "permission_privilege",
            joinColumns = {@JoinColumn(name = "permission_id"), },
            inverseJoinColumns = {@JoinColumn(name = "privilege_id")})
    private Set<Privilege> privileges = new HashSet<>();
}
