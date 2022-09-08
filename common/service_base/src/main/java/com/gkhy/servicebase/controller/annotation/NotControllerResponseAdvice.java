package com.gkhy.servicebase.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Name: NotControllerResponseAdvice
 * @Description:
 * @Author: leo
 * @Created: 2022-09-08
 * @Updated: 2022-09-08
 * @Version: 1.0
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotControllerResponseAdvice {
}
