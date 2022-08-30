package com.gkhy.eduservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {
    private Long id;

    private Long teacherId;

    private Long subjectId;

    private String title;

    // 0.01
    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;
}
