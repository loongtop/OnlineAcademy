package com.gkhy.servicebase.result.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Name: StatusCode
 * @Description:  global unified public response code enumeration

 *
 *
 * @Author: leo
 * @Created: 2022-07-06
 * @Updated: 2022-07-06
 * @Version: 1.0
 **/
@AllArgsConstructor
@Getter
public enum StatusCode implements IStatusCode {

    SUCCESS(90000, "Request succeeded"),
    FAILED(90001, "Request failed"),

    PARAMS_VALIDATE_ERROR(90002, "Parameters verification failed!"),
    RESPONSE_PACK_ERROR(90003, "response returned packaging failed!"),
    ITEM_NOT_FOUND_ERROR(90004,"Can not find item in the database!"),
    DATA_NO_EXIST(90005, "Data does not exist!"),
    PARAMS_ERROR(90006,"Parameters verification failed!"),
    PARAMS_FRONTEND_ERROR(90007,"Parameters from front end are incomplete!"),

    DELETE_ERROR(90010,"Delete failed"),
    SAVE_ERROR(90011,"Add failed"),
    UPDATE_ERROR(90012,"Update failed"),
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private final int code;
    private final String message;
}
