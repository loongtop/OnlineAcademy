package com.gkhy.eduservice.controller;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.repository.TeacherRepository;
import com.gkhy.eduservice.service.TeacherService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.redis.RedisService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * teacher controller
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacher")
public final class TeacherController
        extends ControllerBase<TeacherEntity, Long, TeacherRepository> {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherRepository teacherRepository, TeacherService teacherService) {
        super(teacherRepository);
        this.teacherService = teacherService;
    }

    //Method of conditional query with pagination
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable int current, @PathVariable int limit,
                                       @RequestBody(required = false) TeacherVo teacherQuery) {

        Page<TeacherEntity> eduTeacherList = teacherService.findAll(teacherQuery, current, limit);

        long total = eduTeacherList.getNumberOfElements ();

        return Result.success().data("total",total).data("rows",eduTeacherList.getContent());
   }


}


