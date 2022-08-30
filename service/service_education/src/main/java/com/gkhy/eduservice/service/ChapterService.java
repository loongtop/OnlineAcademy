package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.ChapterEntity;
import com.gkhy.eduservice.entity.chapter.ChapterVo;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * course service
 * </p>
 *
 * @author leo
 * @since 2022-07-20
 */

public interface ChapterService {

    //List of course syllabus, query according to course id
    List<ChapterVo> getChapterVideoByCourseId(Long courseId);

    void deleteById(Long chapterId);

    Optional<ChapterEntity> findById(Long id);

    void removeById(Long courseId);

    ChapterEntity update(Object chapterForm, ChapterEntity chapterEntity);

    ChapterEntity save(ChapterEntity chapter);
}
