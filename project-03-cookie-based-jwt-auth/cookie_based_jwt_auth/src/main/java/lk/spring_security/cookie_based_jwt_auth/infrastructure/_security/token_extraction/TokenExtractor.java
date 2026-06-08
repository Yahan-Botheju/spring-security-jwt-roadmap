package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.token_extraction;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface TokenExtractor {
    //define token extractor
    Optional<String> extractTokenFromCookie(HttpServletRequest request);
}
