package lk.spring_security.refresh_token.web._shared.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {
    //access token set as Http only cookie
    void setAccessTokenCookie(HttpServletResponse response, String accessToken);

    //refresh toke as http only cookie
    void setRefreshTokenCookie(HttpServletResponse response, String refreshToken);

    //clear tokens
    void clearCookies(HttpServletResponse response);

    //read cookie by request
    String clearAuthCookies(HttpServletRequest request, String cookieName);

}
