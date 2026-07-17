package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._wrapperBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.user_spring_wrapper.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class CustomUserDetailsServiceBeanConfig {
    @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepository
    ) {
        return new CustomUserDetailsService(userRepository);
    }
}
