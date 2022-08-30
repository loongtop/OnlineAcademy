package com.gkhy.msmservice.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.msmservice.service.MsmService;
import com.gkhy.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        //1 Get the verification code from redis, if you get it, return it directly
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.hasLength(code)) {
            return Result.success();
        }

        //2 If redis can't get it, send it to Alibaba Cloud
        //Generate a random value and pass it to Alibaba Cloud for sending
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //Method of calling service to send SMS
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //Send successfully, put the successful verification code in redis
            //set valid time
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.success();
        } else {
            return Result.fail().message("Failed to send message!");
        }
    }
}
