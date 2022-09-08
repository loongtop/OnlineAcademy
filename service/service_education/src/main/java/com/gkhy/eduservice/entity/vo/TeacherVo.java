package com.gkhy.eduservice.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TeacherVo {

    @NotNull
    private String name;

    @NotNull
    private Integer level;

    @NotNull
    private String begin;

    @NotNull
    private String end;
}
