package com.gkhy.eduservice.entity.form;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author leo
 * @since 2022-08-01
 *
 */

@Data
public class ChapterForm implements Serializable {

    private static final long serialVersionUID = -6662001959139322064L;

    private Long id;

    private Long courseId;

    private String title;

    private Integer sort;
}
