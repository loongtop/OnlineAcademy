package com.gkhy.rbacservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.gkhy.rbacservice.entity.group.Group;
import com.gkhy.rbacservice.entity.userDetails.UserDetails;
import com.gkhy.rbacservice.entity.userDetails.UserMembership;
import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password", "userDetails", "userMembership"})
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class UserRbac extends DateModel {

    private static final long serialVersionUID = -3305026565378999693L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "name", nullable = false)
    protected String name;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    protected String password;

    @Email
    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "email_verified", nullable = false)
    protected Boolean emailVerified = false;

    @Column(name = "description")
    protected String description;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    @Column(name = "imageUrl")
    private String imageUrl;

    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;

    @OneToOne(mappedBy = "user")
    private UserMembership userMembership;

    /**
     * Configure a many-to-many relationship users and roles
     * 1. Declare the configuration of the table relationship
     * @ManyToMany(targetEntity = Role.class)
     * targetEntity: Entity class bytecode representing the other party
     *
     * 2. Configure the intermediate table (including two foreign keys)
     * @JoinTable
     * name : the name of the intermediate table
     *
     * @joinColumns: configure the foreign key of the current object in the intermediate table
     * Array of @JoinColumn
     * name: foreign key name
     * referencedColumnName: The primary key name of the referenced primary table
     * inverseJoinColumns: Configure the foreign key of the opposite object in the intermediate table
     */
    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id"), },
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(targetEntity = Group.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_group",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<Group> groups = new HashSet<>();
}
