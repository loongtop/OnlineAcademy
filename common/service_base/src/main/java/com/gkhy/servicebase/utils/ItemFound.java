package com.gkhy.servicebase.utils;

import com.gkhy.servicebase.result.Result;
import com.gkhy.servicebase.result.status.StatusCode;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ItemFound {

    public static Result fail() {
        return Result.fail().codeAndMessage(StatusCode.ITEM_NOT_FOUND_ERROR);
    }
}
