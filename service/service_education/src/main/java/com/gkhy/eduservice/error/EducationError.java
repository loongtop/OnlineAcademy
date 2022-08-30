package com.gkhy.eduservice.error;

import com.gkhy.servicebase.error.IAcademyError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EducationError implements IAcademyError {

    DELETE_ERROR(20012,"Delete failed"),
    FILE_IS_EMPTY(20011, "The file is empty"),
    ;

    private static final long serialVersionUID = -6662001959139322064L;

    private final int code;
    private final String message;
}
