package com.gkhy.rbacservice.entity.permission;

import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Privilege extends DateModel {

    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "permission_id")
    private Long permissionId;

    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private Resource resource;

    @JoinColumn(name = "id",referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private Operation operation;

    private String name;

    private String defaultValue;

    private String description;

}