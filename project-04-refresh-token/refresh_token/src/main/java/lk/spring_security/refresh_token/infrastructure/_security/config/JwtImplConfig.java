package lk.spring_security.refresh_token.infrastructure._security.config;

import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.infrastructure._security.JwtImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtImplConfig {
    @Bean
    public TokenService tokenService(
            SecretKey secretKey
    ) {
        return new JwtImpl(secretKey);
    }
}
