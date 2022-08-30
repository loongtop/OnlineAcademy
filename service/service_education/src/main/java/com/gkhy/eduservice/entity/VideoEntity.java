package com.gkhy.eduservice.entity;

import com.gkhy.servicebase.DateModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "视频ID")
    private Long id;

    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @ApiModelProperty(value = "章节ID")
    private Long chapterId;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    private Long videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String videoOriginalName;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "播放次数")
    private Long playCount;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    private Boolean isFree;

    @ApiModelProperty(value = "视频时长（秒）")
    private Float duration;

    @ApiModelProperty(value = "Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @ApiModelProperty(value = "视频源文件大小（字节）")
    private Long size;

    @ApiModelProperty(value = "乐观锁")
    private Long version;
}
