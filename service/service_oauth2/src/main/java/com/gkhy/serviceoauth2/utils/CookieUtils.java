package com.gkhy.serviceoauth2.utils;

import com.gkhy.serviceoauth2.error.Oauth2Error;
import lombok.experimental.UtilityClass;
import org.springframework.util.ObjectUtils;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Optional;

@UtilityClass
public class CookieUtils {

    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (ObjectUtils.isEmpty(cookies)){
            throw new NoSuchElementException(Oauth2Error.COOKIES_NOT_FOUND_IN_REQUEST.getMessage());
        }

        return Arrays.stream(cookies).filter(c-> c.getName() == name).findFirst();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);

        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();

        if (ObjectUtils.isEmpty(cookies)){
            throw new NoSuchElementException("Can not find cookies ! Delete Cookie failed");
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
