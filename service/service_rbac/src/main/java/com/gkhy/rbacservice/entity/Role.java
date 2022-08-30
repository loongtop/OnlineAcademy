package com.gkhy.rbacservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "acl_role")
public class Role extends DateModel {

    private static final long serialVersionUID = 690845200839397661L;

    @Id
    private Long id;

    private String roleName;

    private String roleCode;

    private String remark;

    private Boolean isDeleted;

}
