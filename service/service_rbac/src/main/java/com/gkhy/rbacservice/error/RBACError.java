package com.gkhy.rbacservice.error;

import com.gkhy.servicebase.error.IAcademyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RBACError implements IAcademyError {

    LOGIN_FAILED(20000, "Login failed!"),
    INVALIDED_USERNAME_PASSWORD(20001, "Login failed, wrong username or password!"),
    REGISTER_FAILED(20002, "Registration failed"),
    INVALID_SIGNATURE(20003, "Invalid JWT signature!"),
    INVALID_JWT_TOKEN (20004, "Invalid JWT token!"),
    EXPIRED_JWT_TOKEN(20005, "Expired JWT token!") ,
    UNSUPPORTED_JWT_TOKEN(20006, "Unsupported JWT token!"),
    EMPTY_JWT_TOKEN(20007, "JWT claims string is empty!"),
    SET_AUTHENTICATION_FAILED(20008, "Could not set user authentication in security context"),
    EMAIL_OCCUPIED(20009, "Email has address already occupied!")  ,
    EMAIL_NOT_FOUND_FROM_OAUTH2(20010, "Email has not found from OAuth2 provider!"),
    EMAIL_NOT_FOUND_FROM_DATABASE(20011, "Email has not found from database!"),
    COOKIES_NOT_FOUND_IN_REQUEST(20012, "Cookies has not found in  HttpRequest!"),

    OAUTH2_GENERAL_ERROR(20099, "error")
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private final int code;
    private final String message;

    }
