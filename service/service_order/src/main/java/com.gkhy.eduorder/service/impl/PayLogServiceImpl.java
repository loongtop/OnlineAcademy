package com.gkhy.eduorder.service.impl;

import com.gkhy.eduorder.repository.PayLogRepository;
import com.gkhy.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PayLogServiceImpl implements PayLogService {

    private final PayLogRepository payLogRepository;
    @Autowired
    public PayLogServiceImpl(PayLogRepository payLogRepository) {
        this.payLogRepository = payLogRepository;
    }

    @Override
    public Map<String, Object> createWxNativePay(String orderNo) {
        return null;
    }
}
