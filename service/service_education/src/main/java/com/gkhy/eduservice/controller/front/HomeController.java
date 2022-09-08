package com.gkhy.eduservice.controller.front;

import com.gkhy.eduservice.entity.CourseEntity;
import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.service.CourseService;
import com.gkhy.eduservice.service.TeacherService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("edu/homeFront")
public final class HomeController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    @Autowired
    public HomeController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }
}
