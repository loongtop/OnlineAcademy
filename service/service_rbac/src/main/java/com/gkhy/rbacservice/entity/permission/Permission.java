package com.gkhy.rbacservice.entity.permission;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gkhy.rbacservice.entity.Department;
import com.gkhy.rbacservice.entity.Role;
import com.gkhy.rbacservice.entity.group.Group;
import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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
@JsonIgnoreProperties(value = {"roles", "privilege"})
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "action_resource", "url" }) })
public final class Permission extends DateModel {

    private static final long serialVersionUID = -4961118546104218207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "action_resource", nullable = false)
    private String actionResource;

    @Column(name = "url", nullable = false)
    private String url;

    private String resourceId;

    private String icon;


    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    @Transient
    private Set<?> permissions;

    @ManyToMany(targetEntity = Role.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(targetEntity = Department.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "department_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id")})
    private Set<Department> departments = new HashSet<>();

    @ManyToMany(targetEntity = Group.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "group_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<Group> groups = new HashSet<>();
}
