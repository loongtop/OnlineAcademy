package com.gkhy.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {

    private String id;

    private String title;

    //a subsection
    private List<VideoVo> children = new ArrayList<>();
}
