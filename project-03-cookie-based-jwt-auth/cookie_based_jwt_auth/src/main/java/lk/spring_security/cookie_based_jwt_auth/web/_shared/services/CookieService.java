package lk.spring_security.cookie_based_jwt_auth.web._shared.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieService {


    /* ----- CREATE COOKIE ----- */

    //create cookie name
    @Value("${app.security.cookie-name}")
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
}
