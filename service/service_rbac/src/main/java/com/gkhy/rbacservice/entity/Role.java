package com.gkhy.rbacservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Role
 * </p>
 *
 * @author leo
 * @since 2022-07-12
 */

@Setter
@Getter
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 690845200839397661L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    @Column(name = "ROLE_CODE", nullable = false)
    private String roleCode;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "IS_REMOVED", nullable = false)
    private Boolean enabled = Boolean.TRUE;

//    List<Permission> permissions;

}
