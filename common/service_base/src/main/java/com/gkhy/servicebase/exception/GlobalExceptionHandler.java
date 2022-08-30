package com.gkhy.servicebase.exception;

import com.gkhy.servicebase.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody //to return data
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail().message("Global exception handling performed..");
    }


    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail().message("ArithmeticException exception handling performed..");
    }

    //custom exception
    @ExceptionHandler(AcademyException.class)
    @ResponseBody
    public Result error(AcademyException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

}
