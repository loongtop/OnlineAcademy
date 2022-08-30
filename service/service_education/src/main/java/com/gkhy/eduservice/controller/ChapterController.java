package com.gkhy.eduservice.controller;

import com.gkhy.eduservice.entity.ChapterEntity;
import com.gkhy.eduservice.entity.chapter.ChapterVo;
import com.gkhy.eduservice.entity.form.ChapterForm;
import com.gkhy.eduservice.service.ChapterService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Course Front Controller
 * </p>
 *
 * @author leo
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public final class ChapterController {

    private final ChapterService eduChapterService;
    @Autowired
    public ChapterController(ChapterService eduChapterService) {
        this.eduChapterService = eduChapterService;
    }

    //List of course syllabus, query according to course id
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable Long courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return Result.success().data("allChapterVideo", list);
    }

    @PostMapping("add")
    public Result addChapter(@RequestBody ChapterForm chapterForm) {
        ChapterEntity chapter = new ChapterEntity();
        BeanUtils.copyProperties(chapterForm,chapter);

        ChapterEntity entity = eduChapterService.save(chapter);
        return Result.success().data("item", entity);
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public Result getChapterInfo(@PathVariable Long chapterId) {
        Optional<ChapterEntity> chapterEntity = eduChapterService.findById(chapterId);
        if (chapterEntity.isEmpty()) {
            return ItemFound.fail();
        }
        return Result.success().data("teacher", chapterEntity.get());

    }

    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody ChapterForm chapterForm) {
        Long id = chapterForm.getId();
        Optional<ChapterEntity> chapter = eduChapterService.findById(id);
        if (chapter.isEmpty()) return ItemFound.fail();

        ChapterEntity entity = eduChapterService.update(chapterForm, chapter.get());
        return Result.success().data("item", entity);
    }

    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@PathVariable Long chapterId) {
        Optional<ChapterEntity> chapter = eduChapterService.findById(chapterId);
        if (chapter.isEmpty()) return ItemFound.fail();

        return Result.success();
    }
}

