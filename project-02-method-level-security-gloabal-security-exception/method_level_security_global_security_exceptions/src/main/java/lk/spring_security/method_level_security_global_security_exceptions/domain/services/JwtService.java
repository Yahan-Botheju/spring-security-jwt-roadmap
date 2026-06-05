package lk.spring_security.method_level_security_global_security_exceptions.domain.services;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;

import java.util.Map;

public interface JwtService {
    String generateToken(User user);
    String generateToken(Map<String, Object> extractClaims, User user);
    String extractUsername(String token);
    boolean isTokenValid(String token, User user);


}
