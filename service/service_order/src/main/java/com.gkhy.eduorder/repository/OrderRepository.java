package com.gkhy.eduorder.repository;

import com.gkhy.eduorder.entity.Order;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends IRepositoryBase<Order, Long> {
}

