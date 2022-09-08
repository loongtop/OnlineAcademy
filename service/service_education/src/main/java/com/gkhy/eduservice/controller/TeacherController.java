package com.gkhy.eduservice.controller;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.repository.TeacherRepository;
import com.gkhy.eduservice.service.TeacherService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.result.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

/**
 * @Name: TeacherController
 * @Description:
 * @Author: leo
 * @Created: 2022-09-08
 * @Updated: 2022-09-08
 * @Version: 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacher")
public final class TeacherController {

    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //Method of conditional query with pagination
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable @Min(1) int current, @PathVariable @Min(1) int limit,
                                       @RequestBody(required = false) TeacherVo teacherQuery) {

        Page<TeacherEntity> eduTeacherList = teacherService.findAll(teacherQuery, current, limit);

        long total = eduTeacherList.getNumberOfElements ();

        return Result.success().data("total",total).data("rows",eduTeacherList.getContent());
   }
}
