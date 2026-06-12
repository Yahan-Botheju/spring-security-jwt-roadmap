package lk.spring_security.cookie_based_jwt_auth.web._shared.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.token_extraction.TokenExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class HttpCookieProvider implements TokenExtractor {


    /* ----- CREATE COOKIE ----- */

    //create cookie name
    @Value("${application.security.cookie-name}")
    private String cookieName;

    //initiate expire time
    private static final int COOKIE_EXPIRY = 24 * 60 * 60;

    //JWT token set as Http only cookie to browser
    public void setAuthCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, token)
                .httpOnly(true)         //enable to js cannot read token
                .secure(false)
                .path("/")              //enable for all routes
                .maxAge(COOKIE_EXPIRY)  //set expiry time
                .sameSite("Strict")     //protect from CSRF
                .build();

        //set cookie as http response to header
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    /* ----- READ COOKIE ----- */


    public Optional<String> extractTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }
        //get token from cookies
        return  Arrays.stream(request.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }


    /* ----- REMOVE COOKIE ----- */

    public void  clearAuthCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, null)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
