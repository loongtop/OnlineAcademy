package com.gkhy.eduorder.controller;

import com.gkhy.commonutils.jwt.JwtUtils;
import com.gkhy.eduorder.service.OrderService;
import com.gkhy.servicebase.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * OrderService
 * </p>
 *
 * @author leo
 * @since 2022-07-11
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request) {

        //创建订单，返回订单号
        String orderNo = orderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));

        return Result.success().data("orderNo", orderNo);
    }

    @GetMapping("/getOrderInfo/{orderNo}")
    public Result getOrderInfo(@PathVariable String orderNo) {

        return Result.success();
    }

    @GetMapping("/isBuyCourse/{courseId}/{memberId}")
    public Boolean isBoughtCourse(@PathVariable String courseId, @PathVariable String memberId) {

        long count = orderService.count();

        return true;

    }
}

