package lk.spring_security.stateless_jwt.infrastructure.security.config;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.security.CustomUserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomUserDetailsBeanConfig {
    @Bean
    public CustomUserDetailsImpl customUserDetailsImpl (UserRepository userRepository) {
        return new CustomUserDetailsImpl(userRepository);
    }
}
