package com.gkhy.eduservice.controller;

import com.gkhy.eduservice.entity.subject.MainSubject;
import com.gkhy.eduservice.error.EducationError;
import com.gkhy.eduservice.service.SubjectService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * EduSubject controller
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */

@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public final class SubjectController {

    private final SubjectService subjectService;
    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("add")
    public Result addSubject(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail().data("message", EducationError.FILE_IS_EMPTY.getMessage());
        }

        //get the Excel file
        subjectService.saveSubject(file, subjectService);
        return Result.success();
    }

    @GetMapping("all")
    // first class category (Tree)
    public Result findAll() {
        List<MainSubject> list = subjectService.getAllOneTwoSubject();
        return Result.success().data("list", list);
    }

}
