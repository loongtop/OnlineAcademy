package com.gkhy.servicebase.utils;

import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.error.AcademyError;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemFound {

    public static Result fail() {
        return Result.fail().code(AcademyError.ITEM_NOT_FOUND_ERROR.getCode())
                .message(AcademyError.ITEM_NOT_FOUND_ERROR.getMessage());
    }
}
