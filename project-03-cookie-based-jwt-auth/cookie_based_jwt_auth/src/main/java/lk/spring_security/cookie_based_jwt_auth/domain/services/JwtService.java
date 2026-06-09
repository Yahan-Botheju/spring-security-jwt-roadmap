package lk.spring_security.cookie_based_jwt_auth.domain.services;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;

import java.util.Map;

public interface JwtService {
    String generateToken(User user);
    String generateToken(Map<String,Object> extractClaims, User user);
    String extractUserName(String token);
}
