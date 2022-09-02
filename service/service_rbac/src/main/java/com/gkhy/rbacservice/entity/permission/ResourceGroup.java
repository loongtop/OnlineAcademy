package com.gkhy.rbacservice.entity.permission;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @ClassName ResourceGroup
 * @Description: the group of resourves
 * @Author: leo
 * @CreatedDate: 2022-08-31
 * @UpdatedDate: 2022-08-31
 * @Version: 1.0
 **/
@Setter
@Getter
@Entity
public class ResourceGroup {
    private static final long serialVersionUID = 690845200839397661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;
}
