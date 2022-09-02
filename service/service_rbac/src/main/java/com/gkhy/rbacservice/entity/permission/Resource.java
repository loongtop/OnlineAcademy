package com.gkhy.rbacservice.entity.permission;

import com.gkhy.servicebase.basemodel.DateModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
public class Resource extends DateModel {

    private static final long serialVersionUID = 690845200839397661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;
}
