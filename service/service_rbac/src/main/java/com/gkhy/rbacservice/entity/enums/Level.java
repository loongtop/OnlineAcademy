package com.gkhy.rbacservice.entity.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
public enum Level {
    BEGINNER,
    INTERMEDIATE,
    ADVANCE,
    ;

    private Integer level;

}
