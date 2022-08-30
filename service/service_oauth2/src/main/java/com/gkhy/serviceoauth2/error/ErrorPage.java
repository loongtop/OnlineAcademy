package com.gkhy.serviceoauth2.error;


import com.gkhy.servicebase.error.IAcademyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorPage implements IAcademyError {

    ERROR_PAGE_403(90403, "authorised not enough!")
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private final int code;
    private final String message;
}
