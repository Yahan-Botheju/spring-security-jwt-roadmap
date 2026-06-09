package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.config;

import lk.spring_security.cookie_based_jwt_auth.domain.services.JwtService;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.JwtImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtSecurityKeyConfig {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Bean
    public JwtService  jwtService(){
        return new JwtImpl(secretKey);
    }
}
