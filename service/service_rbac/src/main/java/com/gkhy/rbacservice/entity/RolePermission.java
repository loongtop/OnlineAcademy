package com.gkhy.rbacservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "acl_role_permission")
public class RolePermission extends DateModel {

    private static final long serialVersionUID = -6234543097517329134L;
    @Id
    private Long id;

    private Long roleId;

    private Long permissionId;

    private Boolean isDeleted;

}

