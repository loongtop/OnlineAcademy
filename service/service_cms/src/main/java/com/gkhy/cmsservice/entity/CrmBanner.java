package com.gkhy.cmsservice.entity;

import com.gkhy.servicebase.DateModel;
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
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;
}
