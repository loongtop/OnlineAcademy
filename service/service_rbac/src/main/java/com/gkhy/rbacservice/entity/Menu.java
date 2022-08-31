package com.gkhy.rbacservice.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class Menu implements Serializable {

    @Id
    private Long id;

    private String name;

    private String icon;
}
