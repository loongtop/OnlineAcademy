package com.gkhy.rbacservice.entity;

import com.gkhy.rbacservice.entity.permission.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Name: menu
 * @Description:
 * @Author: leo
 * @Created: 2022-09-12
 * @Updated: 2022-09-12
 * @Version: 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private String icon;

    @OneToOne(targetEntity = Permission.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "menu_permission",
            joinColumns = {@JoinColumn(name = "menu_id"), },
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Permission permission;
}
