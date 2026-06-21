package lk.spring_security.refresh_token.domain.repositories;

import lk.spring_security.refresh_token.domain.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface TokenService {
    String generateToken(User user);
    String generateToken(Map<String, Object> extractClaims, User user);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
