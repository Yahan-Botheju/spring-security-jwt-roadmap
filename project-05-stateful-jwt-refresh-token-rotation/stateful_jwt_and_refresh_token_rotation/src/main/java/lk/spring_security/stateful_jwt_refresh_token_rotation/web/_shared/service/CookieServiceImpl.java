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
    @Value("${application.security.cookie-name}")
    private final String COOKIE_NAME;

    @Value("${application.security.cookie.refresh-token-expiry-seconds}")
    private final long REFRESH_TOKEN_EXPIRY;

    public CookieServiceImpl(String cookieName, long refreshTokenExpiry) {
        this.COOKIE_NAME = cookieName;
        this.REFRESH_TOKEN_EXPIRY = refreshTokenExpiry;
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
}
