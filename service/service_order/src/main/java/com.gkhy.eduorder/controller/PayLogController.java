package com.gkhy.eduorder.controller;


import com.gkhy.servicebase.result.Result;
import com.gkhy.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * <p>
 * Pay Log
 * </p>
 *
 * @author leo
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/order/pay")
@CrossOrigin
public class PayLogController {

    private final PayLogService payLogService;

    @Autowired
    public PayLogController(PayLogService payLogService) {
        this.payLogService = payLogService;
    }

    @GetMapping("/createNative/{orderNo}")
    public Result createWxNativePay(@PathVariable String orderNo){
        //返回的信息包括二维码地址，还需要其他的信息

        Map<String,Object> map = payLogService.createWxNativePay(orderNo);
        return Result.success().data(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        return Result.success();
    }

}

