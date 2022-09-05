package com.gkhy.rbacservice.entity.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Name: Gender
 * @Description:
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Getter
@NoArgsConstructor
public enum Gender {
    NONE,
    FEMALE,
    MALE,
    ;

    private static final long serialVersionUID = -3305026565378999693L;
    private Integer level;
}
