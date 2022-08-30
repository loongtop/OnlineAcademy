package com.gkhy.eduorder.client;

import com.gkhy.commonutils.ordervo.CourseWebVoOrder;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
//@FeignClient("service-edu")
public interface EduClient {

    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);

}
