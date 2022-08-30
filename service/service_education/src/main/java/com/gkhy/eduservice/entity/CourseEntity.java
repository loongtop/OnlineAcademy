package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * Course
 * </p>
 *
 * @author leo
 * @since 2022-07-13
 */

@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class CourseEntity extends DateModel implements Serializable {

    private static final long serialVersionUID = 1581724162696724325L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Course_sequence"
    )
    private Long id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;

    private Integer isDeleted;
}
