package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._jwtBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtBeanConfig {
    @Bean
    public TokenService tokenService(
            SecretKey secretKey,
            @Value("${application.security.cookie.access-token-expiry-seconds}") long accessTokenExpirySeconds,
            @Value("${application.security.cookie.refresh-token-expiry-seconds}")  long refreshTokenExpirySeconds
    ) {
        long accessTokenExpirationMs = accessTokenExpirySeconds * 1000L;
        long refreshTokenExpirationMs = refreshTokenExpirySeconds * 1000L;
        return new JwtServiceImpl(secretKey, accessTokenExpirationMs, refreshTokenExpirationMs);
    }
}
