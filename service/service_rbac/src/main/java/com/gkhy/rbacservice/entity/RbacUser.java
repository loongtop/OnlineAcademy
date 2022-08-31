package com.gkhy.rbacservice.entity;

import com.gkhy.servicebase.basemodel.DateModel;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.List;

/**
 * <p>
 * Rbac User
 * use the user in the model service_base
 * </p>
 *
 * @author leo
 * @since 2022-07-12
 */

public class RbacUser extends DateModel {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    private String salt;

    private String token;

    private Boolean enabled = Boolean.TRUE;

    @Transient
    private List<?> permissions;
    @ManyToMany
    @JoinTable(name = "SysRolePermission",
            joinColumns = {@JoinColumn(name = "permissionId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;
}
