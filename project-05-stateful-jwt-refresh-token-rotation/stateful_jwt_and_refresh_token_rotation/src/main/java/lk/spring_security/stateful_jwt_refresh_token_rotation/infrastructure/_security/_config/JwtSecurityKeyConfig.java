package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security._config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtSecurityKeyConfig {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;


    @Bean
    public SecretKey secretKey() {
        byte[] keyBytes = this.secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
