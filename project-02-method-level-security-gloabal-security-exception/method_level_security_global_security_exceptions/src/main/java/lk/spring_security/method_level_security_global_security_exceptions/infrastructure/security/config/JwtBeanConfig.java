package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.JwtImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtBeanConfig {
    @Value("${application.security.jwt.secret-key}")
    private String secret_key;

    @Bean
    public JwtService jwtService() {
        return new JwtImpl(secret_key);
    }
}
