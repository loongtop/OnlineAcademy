package com.gkhy.rbacservice.entity.privilege;

import com.gkhy.rbacservice.entity.enums.Level;
import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Resource
 * @Description: the resource of system
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/
@Setter
@Getter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Resource extends DateModel {

    private static final long serialVersionUID = 690845200839397661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private Level level = Level.BEGINNER;

    @OneToMany(mappedBy = "resource")
    private Set<Action> actions = new HashSet<>();

    private String description;
}
