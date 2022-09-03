package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.entity.vo.TeacherVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    List<TeacherEntity> findAll();

    List<TeacherEntity> findAll(Specification<TeacherEntity> spec);

    Page<TeacherEntity> findAll(Pageable pageable);

    Page<TeacherEntity> findAll(Specification<TeacherEntity> spec, Pageable pageable);

    List<TeacherEntity> findAll(Specification<TeacherEntity> spec, Sort sort);

    Page<TeacherEntity> findAll(TeacherVo teacherQuery, int current, int limit);

    List<TeacherEntity> findAll(Sort sort);

    List<TeacherEntity> findAllOrderByIdDescLimit2();
}
