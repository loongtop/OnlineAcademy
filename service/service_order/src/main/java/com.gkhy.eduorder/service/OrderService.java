package com.gkhy.eduorder.service;

public interface OrderService {

    String createOrder(String courseId, String memberIdByJwtToken);

    long count();
}
