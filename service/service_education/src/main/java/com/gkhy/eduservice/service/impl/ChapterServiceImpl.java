package com.gkhy.eduservice.service.impl;

import com.gkhy.eduservice.entity.ChapterEntity;
import com.gkhy.eduservice.entity.VideoEntity;
import com.gkhy.eduservice.entity.chapter.ChapterVo;
import com.gkhy.eduservice.entity.chapter.VideoVo;
import com.gkhy.eduservice.repository.ChapterRepository;
import com.gkhy.eduservice.service.ChapterService;
import com.gkhy.eduservice.service.VideoService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * EduChapter Service Implementation Class
 * </p>
 *
 * @author leo
 * @since 2022-07-18
 */

@Service
public final class ChapterServiceImpl extends
        ServiceImpl<ChapterEntity, Long, ChapterRepository>
        implements ChapterService {

    private final VideoService videoService;
    @Autowired
    public ChapterServiceImpl(ChapterRepository iRepository, VideoService videoService) {
        super(iRepository);
        this.videoService = videoService;
    }

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(Long courseId) {
        //Create Specification object
        Specification<ChapterEntity> specEduChapter =
                (root, query, cb) -> cb.equal(root.get("courseId"), courseId);
        //1 Query all chapters in the course according to the course id
        List<ChapterEntity> eduChapterList = this.findAll(specEduChapter);

        Specification<VideoEntity> specEduVideo =
                (root, query, cb) -> cb.equal(root.get("courseId"), courseId);
        //Query all the subsections in the course according to the course id
        List<VideoEntity> eduVideoList = videoService.findAll(specEduVideo);

        //Create a list collection for final encapsulation of data
        List<ChapterVo> finalList = new ArrayList<>();

        for(ChapterEntity eduChapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> videoList = new ArrayList<>();
            for(VideoEntity eduVideo : eduVideoList) {
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }
}
