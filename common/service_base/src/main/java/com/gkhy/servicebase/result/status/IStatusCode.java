package com.gkhy.servicebase.result.status;

import java.io.Serializable;

/**
 * @Name: IStatusCode
 * @Description: status code and message definition interface
 * @Author: leo
 * @Created: 2022-07-06
 * @Updated: 2022-07-06
 * @Version: 1.0
 **/

public interface IStatusCode extends Serializable {
    int getCode();
    String getMessage();
}
