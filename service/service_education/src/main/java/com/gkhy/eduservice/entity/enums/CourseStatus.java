package com.gkhy.eduservice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuscoding
 *
 * 课程状态
 */

@AllArgsConstructor
@Getter
public enum CourseStatus {

    DRAFT("Draft"),
    NORMAL("Normal");

    private final String desc;

}
