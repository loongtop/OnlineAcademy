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
public class CourseDescriptionEntity extends DateModel {

    private static final long serialVersionUID = 730107770905520088L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CourseDescription_sequence"
    )
    private Long id;

    private String description;

}
