package com.gkhy.rbacservice.entity.group;

import com.gkhy.rbacservice.entity.permission.Permission;
import com.gkhy.rbacservice.entity.UserRbac;
import com.gkhy.servicebase.basemodel.OperatorModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Group
 * @Description: the group of users
 * @Author: leo
 * @CreatedDate: 2022-09-01
 * @UpdatedDate: 2022-09-01
 * @Version: 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "group_info",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Group extends OperatorModel {

    private static final long serialVersionUID = -3305026565378999693L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    @OneToOne(mappedBy = "group")
    private GroupDetails groupInfoDetails;

    @ManyToMany(mappedBy = "groups")
    private Set<UserRbac> users = new HashSet<>();

    @Column(name = "description")
    protected String description;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<UserRbac> user = new HashSet<>();
}
