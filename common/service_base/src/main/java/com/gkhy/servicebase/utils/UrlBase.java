package com.gkhy.servicebase.utils;

import com.alibaba.fastjson.JSON;
import com.gkhy.servicebase.redis.RedisService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;

/**
 * @Name: Url
 * @Description:
 * @Author: leo
 * @Created: 2022-09-07
 * @Updated: 2022-09-07
 * @Version: 1.0
 **/

public abstract class UrlBase implements Serializable {

    private static final long serialVersionUID = -6662001959139322064L;

    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private RedisService redisService;

    @PostConstruct
    private void getAllUrl1() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<Map<String, String>> listMap = new ArrayList<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            Map<String, String> initMap = new HashMap<>();
            RequestMappingInfo info = entry.getKey();

            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();

            PatternsRequestCondition pattern = info.getPatternsCondition();

            Set<RequestMethod> methods = methodsCondition.getMethods();
            if (!ObjectUtils.isEmpty(pattern) && !CollectionUtils.isEmpty(methods)) {
                if (!CollectionUtils.isEmpty(pattern.getPatterns())) {
//                    System.out.println("Method Name: " + entry.getValue().getMethod().getName());
                    Set<String> patterns = pattern.getPatterns();
//                    System.out.println("Method Url：" + patterns);
                    for (String url : patterns) {
                        initMap.put("url", url);
                        initMap.put("name", url.replaceAll("/", "_").substring(1));
                    }
                    for (RequestMethod requestMethod : methods) {
                        initMap.put("type", requestMethod.toString());
                    }
                }
                listMap.add(initMap);
            }
        }

        redisService.set(appName, JSON.toJSONString(listMap));
    }

}
