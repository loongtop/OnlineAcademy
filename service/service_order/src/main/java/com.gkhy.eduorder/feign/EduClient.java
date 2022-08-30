package com.gkhy.eduorder.feign;

import com.gkhy.servicebase.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-education")
public interface EduClient {

    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    Result getCourseInfoOrder(@PathVariable("id") String id);

}
