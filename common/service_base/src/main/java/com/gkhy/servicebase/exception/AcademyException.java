package com.gkhy.servicebase.exception;

import lombok.*;

import java.util.function.Supplier;

@Setter
@Getter
@AllArgsConstructor
public class AcademyException extends RuntimeException{

    private static final long serialVersionUID = -7198774369366169824L;

    private Integer code;
    private String msg;
}
