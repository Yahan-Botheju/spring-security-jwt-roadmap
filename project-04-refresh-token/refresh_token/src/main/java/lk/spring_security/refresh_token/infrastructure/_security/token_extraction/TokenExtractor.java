package lk.spring_security.refresh_token.infrastructure._security.token_extraction;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface TokenExtractor {
    //initiate token extractor
    Optional<String> extractTokenFromCookie(HttpServletRequest request);
}
