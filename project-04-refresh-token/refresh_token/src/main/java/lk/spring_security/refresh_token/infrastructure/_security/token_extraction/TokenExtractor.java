package lk.spring_security.refresh_token.infrastructure._security.token_extraction;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface TokenExtractor {
    //get Access Token
    Optional<String> extractAccessTokenFromCookie(HttpServletRequest request);

    //get refresh toke
    Optional<String> extractRefreshTokenFromCookie(HttpServletRequest request);
}
