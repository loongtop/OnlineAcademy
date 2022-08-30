package com.gkhy.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//primary classification
@Data
public class MainSubject {
    private Long id;
    private String title;

    //A primary category has multiple secondary categories
    private List<SecondarySubject> children = new ArrayList<>();
}
