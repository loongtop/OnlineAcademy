package com.gkhy.eduservice.controller;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import com.gkhy.eduservice.repository.TeacherRepository;
import com.gkhy.eduservice.service.TeacherService;
import com.gkhy.servicebase.controller.ControllerBase;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public final class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}


