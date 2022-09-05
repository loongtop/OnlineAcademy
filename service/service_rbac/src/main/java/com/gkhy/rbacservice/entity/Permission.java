package com.gkhy.rbacservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gkhy.rbacservice.entity.privilege.Privilege;
import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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
@JsonIgnoreProperties(value = {"roles", "privilege"})
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public final class Permission extends DateModel {

    private static final long serialVersionUID = -4961118546104218207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "permission", fetch = FetchType.EAGER)
    private Privilege privilege;

    private String icon;

    @Column(name = "privilege_id", nullable = false)
    private UUID privilegeId;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    private LocalDateTime expiryTime;

    @Transient
    private Set<?> permissions;

    @ManyToMany(targetEntity = Role.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();
}
