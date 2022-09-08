package com.gkhy.serviceoauth2.error;


import com.gkhy.servicebase.result.status.IStatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorPage implements IStatusCode {

    ERROR_PAGE_403(90403, "authorised not enough!")
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private final int code;
    private final String message;
}
