package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtSecurityKeyConfig {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
}
