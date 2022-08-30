package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.VideoEntity;
import com.gkhy.eduservice.repository.VideoRepository;
import com.gkhy.eduservice.service.VideoService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Video Service
 * </p>
 *
 * @author leo
 * @since 2022-07-18
 */
@Service
public final class VideoServiceImpl extends
        ServiceImpl<VideoEntity, Long, VideoRepository>
        implements VideoService {

    @Autowired
    public VideoServiceImpl(VideoRepository iRepository) {
        super(iRepository);
    }

    @Override
    public Long count(Long chapterId) {
        //Create Specification object
        Specification<VideoEntity> specification =
                (root, query, cb) -> cb.equal(root.get("chapterId"), chapterId);
        return this.count(specification);
    }

}
