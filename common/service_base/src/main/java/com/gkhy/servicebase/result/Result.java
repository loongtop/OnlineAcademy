package com.gkhy.servicebase.result;

import com.gkhy.servicebase.result.status.IStatusCode;
import com.gkhy.servicebase.result.status.StatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * return value
 * This class that returns the result uniformly for front end
 * @author leo
 * @date 2022-06-02
 */

@Getter
@Setter
@NoArgsConstructor
public final class Result implements Serializable {

    private static final long serialVersionUID = -2666368596113433194L;

    private boolean success;
    private Integer code;
    private String message;
    private HashMap<String, Object> data = new HashMap<>();

    public static Result success() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(StatusCode.SUCCESS.getCode());
        r.setMessage(StatusCode.SUCCESS.getMessage());
        return r;
    }

    public boolean isFail(){
        return !this.isSuccess();
    }

    public static Result fail() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(StatusCode.FAILED.getCode());
        r.setMessage(StatusCode.FAILED.getMessage());
        return r;
    }

    private Result fail(Boolean status){
        this.setSuccess(status);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result codeAndMessage(IStatusCode StatusCode){
        this.setCode(StatusCode.getCode());
        this.setMessage(StatusCode.getMessage());
        return this;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.data.putAll(map);
        return this;
    }
}
