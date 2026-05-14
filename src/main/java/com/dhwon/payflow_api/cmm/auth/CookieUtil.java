package com.dhwon.payflow_api.cmm.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class CookieUtil {

    /**
     *  cookie 생성
     *  1. cookieName : 쿠키 명
     *  2. value : 쿠키 값
     *  3. maxAge : 만료기한
     */
    public static Cookie createCookie(String cookieName, String value, int maxAge){
        Cookie token = new Cookie(cookieName,value);
        token.setHttpOnly(true);
        token.setMaxAge(maxAge);
        token.setPath("/");
        token.setSecure(false);
        return token;
    }
    /**
     * 세션쿠키 생성
     **/
    public static Cookie createSessionCookie(String cookieName, String value){
        Cookie token = new Cookie(cookieName,value);
        token.setHttpOnly(true);
        // 만료일 x session쿠키!
        token.setPath("/");
        token.setSecure(false);
        return token;
    }

    /**
     * cookie를 가져온다
     *  1. req : HttpServletRequest 인터페이스 객체
     *  2. cookieName : 쿠키 명
     */
    public static Cookie getCookie(HttpServletRequest req, String cookieName){
        final Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName()))
                return cookie;
        }
        return null;
    }

}
