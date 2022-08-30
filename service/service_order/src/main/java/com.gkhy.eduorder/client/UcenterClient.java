package com.gkhy.eduorder.client;

import com.gkhy.commonutils.ordervo.UcenterMemberOrder;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
//@FeignClient("service-ucenter")
public interface UcenterClient {

    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
