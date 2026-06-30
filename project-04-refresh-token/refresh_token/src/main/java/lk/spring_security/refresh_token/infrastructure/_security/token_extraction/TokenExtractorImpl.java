package lk.spring_security.refresh_token.infrastructure._security.token_extraction;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class TokenExtractorImpl implements  TokenExtractor {

    //create cookie array which finds value by token name
    private Optional<String> extractCookieByName(HttpServletRequest request, String cookieName) {
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals(cookieName)){
                    return Optional.of(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }

    //get access token
    @Override
    public Optional<String> extractAccessTokenFromCookie(HttpServletRequest request) {
        return extractCookieByName(request, "accessToken");
    }

    //get refresh token
    @Override
    public Optional<String> extractRefreshTokenFromCookie(HttpServletRequest request) {
        return extractCookieByName(request, "refreshToken");
    }
}
