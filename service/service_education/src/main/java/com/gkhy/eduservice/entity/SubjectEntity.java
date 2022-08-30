package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>
 * Course subjects
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
@Table(name = "edu_subject")
public class SubjectEntity extends DateModel implements Serializable {

    private static final long serialVersionUID = 366835828919658691L;

    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence"
    )
    @Column(unique = true, nullable = false, length = 19)
    private Long id;

    private String title;

    private Long parentId;

    private Integer sort;

}
