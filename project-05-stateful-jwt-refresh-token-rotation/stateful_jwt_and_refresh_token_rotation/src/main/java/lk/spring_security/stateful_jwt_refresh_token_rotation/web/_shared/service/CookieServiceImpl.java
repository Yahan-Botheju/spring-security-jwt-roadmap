package lk.spring_security.stateful_jwt_refresh_token_rotation.web._shared.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {

    //inject required dependencies

    private final String cookieName;
    private final long refreshTokenExpiry;


    public CookieServiceImpl(
            @Value("${application.security.cookie-name}") String cookieName,
            @Value("${application.security.cookie.refresh-token-expiry-seconds}") long refreshTokenExpiry
    ) {
        this.cookieName = cookieName;
        this.refreshTokenExpiry = refreshTokenExpiry;
    }

    //add refresh token to cookie
    @Override
    public void addRefreshTokenCookie(
            HttpServletResponse httpServletResponse,
            String refreshToken
    ) {
       //use response cookie builder
       ResponseCookie responseCookie = ResponseCookie.from(COOKIE_NAME, refreshToken)
               .httpOnly(true) //anti XSS
               .secure(false)  //for development
               .path("/")      //entire application
               .maxAge(REFRESH_TOKEN_EXPIRY)
               .sameSite("Strict") //anti CSRF
               .build();

       httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    //extract refresh token from cookie
    @Override
    public String extractRefreshTokenFromCookie(HttpServletRequest  httpServletRequest) {
        if(httpServletRequest.getCookies() != null){
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if(COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    //clear cookie when logout
    @Override
    public void clearCookie(HttpServletResponse httpServletResponse) {
        ResponseCookie responseCookie = ResponseCookie.from(COOKIE_NAME, " ")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }
}
