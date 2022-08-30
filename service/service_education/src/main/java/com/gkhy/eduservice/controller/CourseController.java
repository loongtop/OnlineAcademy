package com.gkhy.eduservice.controller;

import com.gkhy.eduservice.entity.CourseEntity;
import com.gkhy.eduservice.entity.enums.CourseStatus;
import com.gkhy.eduservice.entity.vo.CourseInfoVo;
import com.gkhy.eduservice.entity.vo.CoursePublishVo;
import com.gkhy.eduservice.service.CourseService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Course Controller
 * </p>
 *
 * @author leo
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public final class CourseController {

    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //TODO  Perfect conditional query with pagination
    @GetMapping
    public Result getCourseList() {
        List<CourseEntity> list = courseService.findAll();
        return Result.success().data("list",list);
    }

    @PostMapping("add")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {

        Long id = courseService.saveCourseInfo(courseInfoVo);
        return Result.success().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable Long courseId) {
        boolean exists = courseService.existsById(courseId);
        if (exists) return ItemFound.fail();

        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.success().data("courseInfoVo",courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        Long id = courseInfoVo.getId();
        Optional<CourseEntity> course = courseService.findById(id);
        if (course.isEmpty()) return ItemFound.fail();

        CourseEntity entity = courseService.update(courseInfoVo, course.get());
        return Result.success().data("item", entity);
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable Long id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Result.success().data("publishCourse",coursePublishVo);
    }

    //The course is finally published   Modify the course status
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable Long id) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(id);
        //Set Course Publishing Status
        courseEntity.setStatus(CourseStatus.NORMAL.getDesc());

        CourseEntity entity = courseService.save(courseEntity);
        return Result.success().data("item", entity);
    }

    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable Long courseId) {
        courseService.removeById(courseId);
        return Result.success();
    }
}

