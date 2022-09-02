package com.gkhy.rbacservice.entity.permission;

import com.gkhy.servicebase.basemodel.DateModel;
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
public class Operation extends DateModel {

    private static final long serialVersionUID = -4961118546104218207L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;
}
