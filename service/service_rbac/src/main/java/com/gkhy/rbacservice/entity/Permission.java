package com.gkhy.rbacservice.entity;

import com.gkhy.rbacservice.entity.privilege.Privilege;
import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    @OneToMany(mappedBy = "permission")
    private Set<Privilege> privileges = new HashSet<>();

    private String icon;

    @Column(name = "privilege_id", nullable = false)
    private UUID privilegeId;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    private LocalDateTime expiryTime;

    @Transient
    private List<?> permissions;

    @ManyToMany(targetEntity = Role.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Role> roles = new HashSet<>();
}
