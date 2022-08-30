package com.gkhy.eduorder.service.impl;

import com.gkhy.eduorder.entity.Order;
import com.gkhy.eduorder.repository.OrderRepository;
import com.gkhy.eduorder.service.OrderService;
import com.gkhy.servicebase.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class OrderServiceImpl extends
        ServiceImpl<Order, Long, OrderRepository>
        implements OrderService {

    @Autowired
    public OrderServiceImpl(OrderRepository iRepository) {
        super(iRepository);
    }

    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {
        return null;
    }

}
