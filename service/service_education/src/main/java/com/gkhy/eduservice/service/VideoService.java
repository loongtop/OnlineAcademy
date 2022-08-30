package com.gkhy.eduservice.service;

import com.gkhy.eduservice.entity.VideoEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * eduVideo service
 * </p>
 *
 * @author testjava
 * @since 2022-07-18
 */
public interface VideoService {

    Long count(Long chapterId);

    void deleteById(Long chapterId);

    List<VideoEntity> findAll(Specification<VideoEntity> specEduVideo);

    VideoEntity save(VideoEntity eduVideo);

    void removeById(Long id);

    Optional<VideoEntity> findById(Long id);

    VideoEntity update(Object videoForm, VideoEntity videoEntity);
}
