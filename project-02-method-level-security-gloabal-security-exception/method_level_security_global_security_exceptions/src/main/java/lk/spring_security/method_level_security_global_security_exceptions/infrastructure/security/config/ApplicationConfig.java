package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    //hashed password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //check username and password while user login
    @Bean
    public AuthenticationManager authenticationManager(
                AuthenticationConfiguration authConfiguration
    ) {
        return authConfiguration.getAuthenticationManager();
    }
}
