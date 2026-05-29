package lk.spring_security.method_level_security_global_security_exceptions.domain.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> extractClaims, UserDetails userDetails);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);


}
