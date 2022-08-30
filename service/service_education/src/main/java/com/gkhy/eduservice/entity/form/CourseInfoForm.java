package com.gkhy.eduservice.entity.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author leo
 * @since 2022-08-01
 *
 */

@Data
public class CourseInfoForm {

    private static final long serialVersionUID = -6662001959139322064L;

    private String id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;
}
