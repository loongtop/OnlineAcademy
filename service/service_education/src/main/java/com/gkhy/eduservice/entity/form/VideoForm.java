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
public class VideoForm implements Serializable {

    private static final long serialVersionUID = -6662001959139322064L;

    private String id;

    private String courseId;

    private String chapterId;

    private String title;

    private String videoSourceId;

    private String videoOriginalName;

    private Integer sort;

    private Long playCount;

    private Boolean isFree;

    private Float duration;

    private String status;

    private Long size;
}
