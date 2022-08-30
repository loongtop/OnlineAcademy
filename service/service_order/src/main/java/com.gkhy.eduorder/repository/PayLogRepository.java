package com.gkhy.eduorder.repository;

import com.gkhy.eduorder.entity.PayLog;
import com.gkhy.servicebase.service.IRepositoryBase;
import org.springframework.stereotype.Repository;

@Repository
public interface PayLogRepository extends IRepositoryBase<PayLog, Long> {

}