package com.gkhy.rbacservice.entity;


import com.gkhy.servicebase.DateModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 * UserRole
 * </p>
 *
 * @author leo
 * @since 2020-01-12
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -7038295656222058541L;

    @Id
    private Long id;

    private Long roleId;

    private Long userId;

    private Boolean isRemoved;

}
