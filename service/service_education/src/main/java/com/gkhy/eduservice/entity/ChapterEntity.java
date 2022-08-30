package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * <p>
 * course
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
public class ChapterEntity extends DateModel{

    private static final long serialVersionUID = -6662001959139322064L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Chapter_sequence"
    )
    private Long id;

    private String courseId;

    private String title;

    private Integer sort;

}
