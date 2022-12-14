package com.gkhy.rbacservice.entity.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Name: Level
 * @Description: the level of resource, action, role
 * @Author: leo
 * @Created: 2022-09-03
 * @Updated: 2022-09-03
 * @Version: 1.0
 **/
@Getter
@NoArgsConstructor
public enum Level implements Serializable {
    BEGINNER,
    INTERMEDIATE,
    ADVANCE,
    ;

    private static final long serialVersionUID = -3305026565378999693L;

    private Integer level;
}
