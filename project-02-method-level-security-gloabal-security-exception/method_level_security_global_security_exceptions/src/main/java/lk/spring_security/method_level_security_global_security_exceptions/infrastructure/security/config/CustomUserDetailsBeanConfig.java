package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomUserDetailsBeanConfig {
    @Bean
    public CustomUserDetailsService customUserDetailsService(
            UserRepository userRepository
    ) {
        return new CustomUserDetailsService(userRepository);
    }
}
