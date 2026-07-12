package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._jwtBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.JwtServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtBeanConfig {
    @Bean
    public TokenService tokenService(
            SecretKey secretKey
    ) {
        return new JwtServiceImpl(secretKey);
    }
}
