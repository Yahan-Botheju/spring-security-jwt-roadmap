package lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
