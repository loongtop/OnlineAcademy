package com.gkhy.servicebase.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * response code enumeration
 *
 * Error code and error message definition class
 * 1. The error code definition rule is that 5 is a number
 * 2. The first two digits represent business scenarios, and the last three digits represent error codes. For example: 100001. 10: General 001: System unknown exception
 * 3. After maintaining the error code, you need to maintain the error description, and define them as an enumeration
 * Error code list:
 *
 * @author leo
 * @Since 2022-07-01
 */
@AllArgsConstructor
@Getter
public enum AcademyError implements IAcademyError {

//    UPLOAD_FILE_ERROR(20010,"File upload failed"),
//    VALID_CODE_SEND_FAIL(20013,"Send SMS verification code failed"),
//
//    GET_PLAY_AUTH_FAIL(20016,"Failed to get video credentials"),
//    REMOTE_CALL_COURSE(20017,"Failed to query course information by remote call"),
//    REMOTE_CALL_MEMBER(20018,"Failed to call member module remotely"),
//    WECHAT_PAY_FAIL(20019,"WeChat payment failed"),
//    PARAMS_ERROR(20020,"Parameter verification failed"),
//    SAVE_ERROR(20003,"Add failed"),
//    UPDATE_ERROR(20004,"Update failed"),
//    DATA_NO_EXIST(20005, "Data does not exist"),
    ITEM_NOT_FOUND_ERROR(90001,"Item was not find in the database!"),
    DELETE_ERROR(20012,"Delete failed"),
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private final int code;
    private final String message;
}
