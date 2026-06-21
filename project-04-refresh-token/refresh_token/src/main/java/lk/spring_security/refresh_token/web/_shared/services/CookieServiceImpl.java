package lk.spring_security.refresh_token.web._shared.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {

    //initiate token existing time
   @Value("${application.security.cookie.access-token-expiry-seconds}")
    private int accessTokenExpiry;

   @Value("${application.security.cookie.refresh-token-expiry-seconds}")
    private int refreshTokenExpiry;

   /* ----- __CREATE TOKENS__ ----- */

    //create access token cookie
    @Override
    public void setAccessTokenCookie(
            HttpServletResponse response,
            String accessToken
    ) {
        createCookie(response, "access_token", accessToken, accessTokenExpiry);
    }

    //create refresh token cookie
    @Override
    public void setRefreshTokenCookie(
            HttpServletResponse response,
            String refreshToken
    ) {
        createCookie(response, "refresh_token", refreshToken, refreshTokenExpiry);
    }

    //clear token
    @Override
    public void clearCookies(HttpServletResponse response) {
        createCookie(response, "access_token", null,0);
        createCookie(response, "refresh_token", null,0);
    }


    //create cookie
    private void createCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge
    ){
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); //false due to localhost
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


}
