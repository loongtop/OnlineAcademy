package com.gkhy.cmsservice.entity;

import com.gkhy.servicebase.basemodel.DateModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 * Home banner sheet
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */

@Setter
@Getter
@Accessors(chain = true)
@Entity
@ApiModel(value="CrmBanner Object", description="Home banner sheet")
public class CrmBanner extends DateModel implements Serializable {

    private static final long serialVersionUID = 4833398331890223365L;

    @Id
    private String id;

    private String title;

    private String imageUrl;

    private String linkUrl;

    private Integer sort;

    private Boolean enabled = Boolean.TRUE;
}
