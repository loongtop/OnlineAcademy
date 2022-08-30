package com.gkhy.servicebase.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
@Component
public final class Result extends HashMap<String, Object> {

    private static final int SUCCESS = 20000;
    private static final int ERROR = 20001;

    private static final long serialVersionUID = -2666368596113433194L;

    private boolean fail;
    private Integer code;
    private String message;

    public static Result success() {
        Result r = new Result();
        r.setFail(false);
        r.setCode(SUCCESS);
        r.setMessage("Success");
        return r;
    }

    public static Result fail() {
        Result r = new Result();
        r.setFail(true);
        r.setCode(ERROR);
        r.setMessage("Failure");
        return r;
    }

    public Result success(Boolean success){
        this.setFail(success);
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

    public Result data(String key, Object value){
        super.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        super.putAll(map);
        return this;
    }
}
