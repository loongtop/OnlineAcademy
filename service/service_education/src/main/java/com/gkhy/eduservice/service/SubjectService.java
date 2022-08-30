package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.SubjectEntity;
import com.gkhy.eduservice.entity.subject.MainSubject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Course subjects Service
 * </p>
 *
 * @author leo
 * @since 2022-07-19
 */

public interface SubjectService {

    void saveSubject(MultipartFile file, SubjectService eduSubjectService);

    List<MainSubject> getAllOneTwoSubject();

    SubjectEntity save(SubjectEntity existOneSubject);

    Optional<SubjectEntity> findOne(Specification<SubjectEntity> specification);
}
