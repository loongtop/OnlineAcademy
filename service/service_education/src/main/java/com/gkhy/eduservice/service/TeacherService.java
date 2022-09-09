package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import org.springframework.data.domain.Page;


public interface TeacherService {
    Page<TeacherEntity> findAll(TeacherVo teacherQuery, int current, int limit);
}
