package lk.spring_security.stateful_jwt_refresh_token_rotation.web._shared.service;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import org.springframework.beans.factory.annotation.Value;
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
}
