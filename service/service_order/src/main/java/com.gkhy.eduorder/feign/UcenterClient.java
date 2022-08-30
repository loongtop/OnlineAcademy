package com.gkhy.eduorder.feign;


import org.springframework.cloud.openfeign.FeignClient;
import com.gkhy.servicebase.result.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-rbac")
public interface UcenterClient {

    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    Result getUserInfoOrder(@PathVariable("id") String id);
}
