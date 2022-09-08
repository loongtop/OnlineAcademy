package com.gkhy.servicebase.controller.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.controller.annotation.NotControllerResponseAdvice;
import com.gkhy.servicebase.result.status.StatusCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @Name: ControllerResponseAdvice
 * @Description:
 * @Author: leo
 * @Created: 2022-09-08
 * @Updated: 2022-09-08
 * @Version: 1.0
 **/
@RestControllerAdvice(basePackages = {"com.gkhy"})
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {


    // The response is of type Result, or it is not wrapped if NotControllerResponseAdvice is annotated
    @Override
    //supports: determine whether to hand over to the beforeBodyWrite method for execution, true: required; false: not required
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {

        // On the enhanced filtering method, the methods that contain this annotation are filtered,
        // and finally the annotations are added to the methods that do not need to be wrapped.
        // For example, the Controller method can return a string
        return !(methodParameter.getParameterType().isAssignableFrom(Result.class)
                || methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class));
    }

    @Override
    public Object beforeBodyWrite(Object data,
                                  MethodParameter returnType,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        if (!Objects.nonNull(data)) return Result.success();

        // String type cannot be directly wrapped
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // Wrap the data in Result and convert it to a json string for return
                return objectMapper.writeValueAsString(Result.success().data("data", data));
            } catch (JsonProcessingException e) {
                throw new AcademyException(StatusCode.RESPONSE_PACK_ERROR.getCode(), e.getMessage());
            }
        }

        return Result.success().data("data", data);
    }
}
