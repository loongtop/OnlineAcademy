package com.gkhy.eduorder.utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Order Number
 *
 * @author leo
 * @since
 */
@UtilityClass
public class OrderNumberUtil {

    public static String getOrderNo() {
        String newDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result.append(random.nextInt(10));
        }
        return newDate + result;
    }

}
