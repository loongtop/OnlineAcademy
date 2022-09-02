package com.gkhy.rbacservice.entity;

import com.gkhy.servicebase.basemodel.OperatorModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Role
 * @Description: the role of the users
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/

@Setter
@Getter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name"}) })
public final class Role extends OperatorModel {

    private static final long serialVersionUID = 690845200839397661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    @ManyToMany(targetEntity = Permission.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private Set<RbacUser> user = new HashSet<>();
}
