package lk.spring_security.refresh_token.web._shared.services;

import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {
    //access token set as Http only cookie
    void setAccessTokenCookie(HttpServletResponse response, String accessToken);
}
