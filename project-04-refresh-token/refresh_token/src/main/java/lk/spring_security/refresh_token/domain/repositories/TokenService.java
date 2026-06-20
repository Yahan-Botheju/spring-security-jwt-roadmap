package lk.spring_security.refresh_token.domain.repositories;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String extractToken(String token);
    String generateAccessToken(UserDetails userDetails, Long userId);
    boolean isTokenValid(String token, UserDetails userDetails);
}
