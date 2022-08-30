package com.gkhy.eduservice.feign;

import com.gkhy.servicebase.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * @author: leo
 * @create: 2022-08-15
 */

@Component
@FeignClient("service-video")
public interface VideoFeignClient {

    /**
     * Define the path of the method call Delete Alibaba Cloud video according to the video id
     * The @PathVariable annotation must specify the parameter name, otherwise an error will occur
     * @param id : video id
     * @return Result
     */

    @DeleteMapping("/eduvod/video/remove/{id}")
    Result removeById(@PathVariable("id") Long id);

    /**
     * Define a method to call multiple videos
     * @param videoIdList : videoIdList multiple video ids
     * @return Result
     */
    @DeleteMapping("/eduvod/video/deleteBatch")
     Result deleteBatch(@RequestParam("videoIdList") List<Long> videoIdList);
}
