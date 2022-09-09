package com.gkhy.eduorder.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

import com.gkhy.servicebase.basemodel.DateModel;

/**
 * <p>
 * order
 * </p>
 *
 * @author leo
 * @since 2022-07-08
 */

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order extends DateModel {

    private static final long serialVersionUID = 4716562029132875978L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String orderNo;

    private Long courseId;

    private String courseTitle;

    private String courseCover;

    private String teacherName;

    private Long memberId;

    private String nickname;

    private String mobile;

    private BigDecimal totalFee;

    private Integer payType;

    private Integer status;

    private Boolean enabled = Boolean.TRUE;

}
