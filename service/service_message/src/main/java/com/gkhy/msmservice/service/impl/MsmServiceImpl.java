package com.gkhy.msmservice.service.impl;

import com.gkhy.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(Map<String, Object> param, String phone) {
        return StringUtils.hasLength(phone);

//        DefaultProfile profile =
//                DefaultProfile.getProfile("default", "LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        //Set related fixed parameters
//        CommonRequest request = new CommonRequest();
//        //request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//
//        //Set sending related parameters
//        request.putQueryParameter("PhoneNumbers",phone); //手机号
//        request.putQueryParameter("SignName","我的谷粒在线教育网站"); //申请阿里云 签名名称
//        request.putQueryParameter("TemplateCode","SMS_180051135"); //申请阿里云 模板code
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递
//
//        try {
//            //send
//            CommonResponse response = client.getCommonResponse(request);
//            boolean success = response.getHttpResponse().isSuccess();
//            return success;
//        }catch(Exception e) {
//            e.printStackTrace();
//            return false;
//        }
    }
}
