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
       ResponseCookie responseCookie = ResponseCookie.from(cookieName, refreshToken)
               .httpOnly(true) //anti XSS
               .secure(false)  //for development
               .path("/")      //entire application
               .maxAge(refreshTokenExpiry)
               .sameSite("Strict") //anti CSRF
               .build();

       httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    //extract refresh token from cookie
    @Override
    public String extractRefreshTokenFromCookie(HttpServletRequest  httpServletRequest) {
        if(httpServletRequest.getCookies() != null){
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if(cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    //clear cookie when logout
    @Override
    public void clearCookie(HttpServletResponse httpServletResponse) {
        ResponseCookie responseCookie = ResponseCookie.from(cookieName, " ")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }

    /* __PRIVATE HELPER_METHOD__ */

    private void createCookie(
            HttpServletResponse httpServletResponse,
            String value,
            long maxAge
    ) {
        ResponseCookie responseCookie = ResponseCookie.from(cookieName, value)
                .httpOnly(true) //anti XSS
                .secure(false)  //for development
                .path("/")      //entire application
                .maxAge(refreshTokenExpiry)
                .sameSite("Strict") //anti CSRF
                .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }
}
