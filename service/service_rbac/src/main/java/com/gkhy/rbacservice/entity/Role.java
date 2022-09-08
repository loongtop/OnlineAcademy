package com.gkhy.rbacservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gkhy.rbacservice.entity.enums.Level;
import com.gkhy.rbacservice.entity.permission.Permission;
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
@JsonIgnoreProperties(value = {"user"})
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name"}) })
public final class Role extends OperatorModel {

    private static final long serialVersionUID = 690845200839397661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private Level level = Level.BEGINNER;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<UserRbac> user = new HashSet<>();
}
