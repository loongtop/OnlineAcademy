package com.gkhy.rbacservice.entity.permission;

import com.gkhy.rbacservice.entity.enums.Level;
import com.gkhy.servicebase.basemodel.OperatorModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @ClassName Operation
 * @Description: the operation of users
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Action extends OperatorModel {

    private static final long serialVersionUID = -4961118546104218207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Level level = Level.BEGINNER;

    private String description;

    @ManyToOne(targetEntity = Resource.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "resource_action",
            joinColumns = {@JoinColumn(name = "action_id"), },
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    private Resource resource;
}
