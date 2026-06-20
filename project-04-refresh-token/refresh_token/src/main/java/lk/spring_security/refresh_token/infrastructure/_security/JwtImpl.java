package lk.spring_security.refresh_token.infrastructure._security;

import lk.spring_security.refresh_token.domain.repositories.TokenService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;

public class JwtImpl implements TokenService {

    //inject required dependencies
    private final SecretKey secretKey;
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000;

    public JwtImpl(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String extractToken(String token) {
        return "";
    }

    @Override
    public String generateAccessToken(UserDetails userDetails, Long userId) {
        return "";
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }
}
