package lk.spring_security.stateless_jwt.config;

import lk.spring_security.stateless_jwt.infrastructure.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    //inject jwt auth filter
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    //inject auth provider
    private final AuthenticationProvider authenticationProvider;
}
