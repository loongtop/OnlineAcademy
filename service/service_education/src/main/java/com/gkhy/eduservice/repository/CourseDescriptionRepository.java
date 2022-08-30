package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.CourseDescriptionEntity;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDescriptionRepository extends IRepositoryBase<CourseDescriptionEntity, Long> {
}

