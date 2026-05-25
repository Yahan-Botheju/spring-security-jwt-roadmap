package lk.spring_security.stateless_jwt.domain.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> extractClaims, UserDetails userDetails);
    String extractUserName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
