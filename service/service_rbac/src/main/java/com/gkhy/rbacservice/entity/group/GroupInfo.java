package com.gkhy.rbacservice.entity.group;

import com.gkhy.rbacservice.entity.RbacUser;
import com.gkhy.servicebase.basemodel.DateModel;
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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class GroupInfo extends DateModel {

    private static final long serialVersionUID = -3305026565378999693L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = Boolean.TRUE;

    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private GroupInfoDetails groupInfoDetails;

    @ManyToMany(mappedBy = "groups")
    private Set<RbacUser> users = new HashSet<>();
}
