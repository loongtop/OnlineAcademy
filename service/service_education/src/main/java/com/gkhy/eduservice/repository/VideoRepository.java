package com.gkhy.eduservice.repository;

import com.gkhy.eduservice.entity.VideoEntity;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends IRepositoryBase<VideoEntity, Long> {
}
