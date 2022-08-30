package com.gkhy.eduservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: leo
 * @create: 2022-07-18
 */

@Component
@FeignClient(value = "service-order")
public interface OrderFeignClient {

    /**
     * Query the order status in the order table according to the course id and user id
     * @param courseId :  course id
     * @param memberId :  member id
     * @return whether to buy
     */

    @GetMapping("/order/isBuyCourse/{courseId}/{memberId}")
    Boolean isBoughtCourse(@PathVariable("courseId") String courseId,
                           @PathVariable("memberId") String memberId);

}
