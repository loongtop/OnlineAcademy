package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.CourseDescriptionEntity;
import com.gkhy.eduservice.repository.CourseDescriptionRepository;
import com.gkhy.eduservice.service.CourseDescriptionService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Course Introduction Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-17
 */
@Service
public final class CourseDescriptionServiceImpl extends
        ServiceImpl<CourseDescriptionEntity, Long, CourseDescriptionRepository>
        implements CourseDescriptionService {

    @Autowired
    public CourseDescriptionServiceImpl(CourseDescriptionRepository iRepository) {
        super(iRepository);
    }

}
