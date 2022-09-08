package com.gkhy.servicebase.controller.advice;

import com.gkhy.servicebase.exception.AcademyException;
import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.result.status.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @Name: ControllerExceptionAdvice
 * @Description:
 * @Author: leo
 * @Created: 2022-09-08
 * @Updated: 2022-09-08
 * @Version: 1.0
 **/
@RestControllerAdvice(basePackages = "com.gkhy")
public class ControllerExceptionAdvice {

    /**
     *
     * 异常日志记录
     *
     * @param e
     */
//    private void logErrorRequest(Exception e) {
//        String info = String.format("报错API URL: %s%nQuery String: %s",
//                httpServletRequest.getRequestURI(),
//                httpServletRequest.getQueryString());
//        ApiLogger.runLogger.error(info);
//        ApiLogger.exceptionLogger.error(e.getMessage(), e);
//        String ipInfo = "报错访问者IP信息：" + httpServletRequest.getRemoteAddr() + "," + httpServletRequest.getRemoteHost();
//        ApiLogger.runLogger.error(ipInfo);
//    }

    @ExceptionHandler({BindException.class})
    public Result MethodArgumentNotValidExceptionHandler(BindException e) {
        // Get the ObjectError object from the exception object
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return Result.fail()
                .code(StatusCode.PARAMS_VALIDATE_ERROR.getCode())
                .message(objectError.getDefaultMessage());
    }

    @ExceptionHandler(AcademyException.class)
    public Result APIExceptionHandler(AcademyException e) {
        //Since the logging framework has not been integrated yet, leave it for now and write TODO
        // log.error(e.getMessage(), e);
        return Result.fail().code(e.getCode()).message(e.getMsg() + "-" + e.getMessage());
    }

    /**
     * {@code @RequestBody}
     * Exception handling thrown when parameter validation fails
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("Verification failed:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        if (StringUtils.hasText(msg)) {
            return Result.fail()
                    .code(StatusCode.PARAMS_VALIDATE_ERROR.getCode())
                    .message(msg);
        }
        return Result.fail()
                .code(StatusCode.PARAMS_VALIDATE_ERROR.getCode());
    }

    /**
     * {@code @PathVariable} and {@code @RequestParam}
     * Exception handling thrown when parameter validation fails
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public Result handleConstraintViolationException(ConstraintViolationException ex) {
        if (StringUtils.hasText(ex.getMessage())) {
            return Result.fail()
                    .code(StatusCode.PARAMS_VALIDATE_ERROR.getCode())
                    .message(ex.getMessage());
        }
        return Result.fail()
                .code(StatusCode.PARAMS_VALIDATE_ERROR.getCode());
    }

    /**
     * Top-level exceptions are captured and handled uniformly, and they are used when other exceptions cannot be handled
     */
    @ExceptionHandler({Exception.class})
    public Result handle(Exception ex) {
        return Result.fail().message(ex.getMessage());
    }
}
