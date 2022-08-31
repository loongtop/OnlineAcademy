package com.gkhy.rbacservice.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * RolePermission
 * </p>
 *
 * @author leo
 * @since 2022-07-12
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = -6234543097517329134L;
    @Id
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Boolean enabled = Boolean.TRUE;

}

