package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.basemodel.DateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * video
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */

@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class VideoEntity extends DateModel {

    private static final long serialVersionUID = -2768812711440831235L;

    @Id
    private Long id;

    private Long courseId;

    private Long chapterId;

    private String title;

    private Long videoSourceId;

    private String videoOriginalName;

    private Integer sort;

    private Long playCount;

    private Boolean isFree;

    private Float duration;

    private String status;

    private Long size;

    private Long version;
}
