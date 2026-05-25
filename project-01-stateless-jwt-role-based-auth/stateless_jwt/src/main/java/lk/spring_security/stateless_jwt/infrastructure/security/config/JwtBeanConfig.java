package lk.spring_security.stateless_jwt.infrastructure.security.config;

import lk.spring_security.stateless_jwt.infrastructure.security.JwtImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtBeanConfig {
    @Value("${application.security.jwt.secret-key}")
    private  String SECRET_KEY;

    @Bean
    public JwtImpl  jwtService() {
        return new JwtImpl(SECRET_KEY);
    }
}
