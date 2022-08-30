package com.gkhy.eduorder.service;

import java.util.Map;


public interface PayLogService {
    Map<String, Object> createWxNativePay(String orderNo);
}
