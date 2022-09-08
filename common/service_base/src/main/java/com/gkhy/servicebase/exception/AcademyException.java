package com.gkhy.servicebase.exception;

import com.gkhy.servicebase.result.status.IStatusCode;
import lombok.*;

import java.util.function.Supplier;

@Setter
@Getter
@AllArgsConstructor
public class AcademyException extends RuntimeException{

    private static final long serialVersionUID = -7198774369366169824L;

    private Integer code;
    private String msg;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public AcademyException(IStatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMessage();
    }
}
