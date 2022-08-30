package com.gkhy.eduorder.entity;

import com.gkhy.servicebase.DateModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * pay log sheet
 * </p>
 *
 * @author leo
 * @since 2020-07-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PayLog extends DateModel {

    private static final long serialVersionUID = 402433662897235817L;

    @Id
    private Long id;

    private String orderNo;

    private Date payTime;

    private BigDecimal totalFee;

    private Long transactionId;

    private String tradeState;

    private Integer payType;

    private String attr;

    private Boolean isDeleted;
}
