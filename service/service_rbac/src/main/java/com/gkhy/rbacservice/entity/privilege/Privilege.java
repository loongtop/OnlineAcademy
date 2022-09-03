package com.gkhy.rbacservice.entity.privilege;

import com.gkhy.rbacservice.entity.Permission;
import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Privilege extends DateModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(targetEntity = Permission.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "permission_privilege",
            joinColumns = {@JoinColumn(name = "permission_id"), },
            inverseJoinColumns = {@JoinColumn(name = "privilege_id")})
    private Permission permission;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "privilege_id", nullable = false)
    private UUID privilegeId;

    private String description;

}