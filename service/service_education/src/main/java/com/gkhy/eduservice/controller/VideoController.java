package com.gkhy.eduservice.controller;

import com.gkhy.eduservice.entity.VideoEntity;
import com.gkhy.eduservice.entity.form.VideoForm;
import com.gkhy.eduservice.error.EducationError;
import com.gkhy.eduservice.feign.VideoFeignClient;
import com.gkhy.eduservice.service.VideoService;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.utils.ItemFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 * Course Video Front Controller
 * </p>
 *
 * @author leo
 * @since 2020-07-18
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public final class VideoController {

    private final VideoService videoService;
    private final VideoFeignClient videoFeignClient;

    @Autowired
    public VideoController(VideoService videoService, VideoFeignClient videoFeignClient) {
        this.videoService = videoService;
        this.videoFeignClient = videoFeignClient;
    }

    @PostMapping("add")
    public Result addVideo(@RequestBody VideoEntity videoEntity) {

        VideoEntity entity = videoService.save(videoEntity);
        return Result.success().data("item", entity);

    }

    // TODO
    //  when deleting a subsection, delete the video inside at the same time
    @DeleteMapping("delete/{id}")
    public Result deleteVideo(@PathVariable Long id) {
        Optional<VideoEntity> video = videoService.findById(id);
        if (video.isEmpty()) return ItemFound.fail();

        Long videoSourceId = video.get().getVideoSourceId();
        Result result = videoFeignClient.removeById(videoSourceId);

        if(result.isFail()){
            return Result.fail()
                    .code(EducationError.DELETE_ERROR.getCode())
                    .message(EducationError.DELETE_ERROR.getMessage()+"Ali yun Video ID " + videoSourceId);
        }

        videoService.removeById(id);
        return Result.success();
    }

    @PutMapping("update")
    public Result updateVideo(@RequestBody VideoForm videoForm){

        VideoEntity videoEntity = new VideoEntity();

        VideoEntity entity = videoService.update(videoForm, videoEntity);
        return Result.success().data("item", entity);
    }

    @GetMapping("get/{id}")
    public Result getVideoById(@PathVariable Long id){

        Optional<VideoEntity> video = videoService.findById(id);
        if (video.isEmpty()) return ItemFound.fail();

        return Result.success().data("item", video);
    }

}

