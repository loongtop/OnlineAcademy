package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.TeacherEntity;
import com.gkhy.eduservice.repository.TeacherRepository;
import com.gkhy.eduservice.service.TeacherService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * EduTeacher Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */

@Service
public class TeacherServiceImpl
        extends ServiceImpl<TeacherEntity, TeacherRepository>
        implements TeacherService {
    public TeacherServiceImpl(TeacherRepository iRepository) {
        super(iRepository);
    }
}
