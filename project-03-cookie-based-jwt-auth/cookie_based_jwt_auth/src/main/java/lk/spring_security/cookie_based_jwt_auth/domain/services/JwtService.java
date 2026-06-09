package lk.spring_security.cookie_based_jwt_auth.domain.services;

public interface JwtService {
    String extractUserName(String token);
}
