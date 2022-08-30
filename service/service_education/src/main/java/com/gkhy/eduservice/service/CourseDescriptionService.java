package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.CourseDescriptionEntity;

import java.util.Optional;

public interface CourseDescriptionService {
    CourseDescriptionEntity save(CourseDescriptionEntity courseDescription);

    void removeById(Long courseId);

    Optional<CourseDescriptionEntity> findById(Long courseId);
}
