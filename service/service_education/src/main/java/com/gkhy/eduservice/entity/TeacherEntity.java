package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * <p>
 * teacher
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name= "edu_teacher")
public class TeacherEntity extends DateModel {

    private static final long serialVersionUID = 1065977948055204551L;

    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    @Column(unique = true, nullable = false, length = 19)
    private Long id;

    @Size(min = 4, max = 64, message = "Minimum username length: 4 characters")
    private String name;

    private String intro;

    private String career;

    private Integer level;

    private String avatar;

    private Integer sort;

    private Boolean isDeleted;

}
