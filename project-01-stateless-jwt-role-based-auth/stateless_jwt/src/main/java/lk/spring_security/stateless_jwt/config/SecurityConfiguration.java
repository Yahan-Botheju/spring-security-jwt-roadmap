package lk.spring_security.stateless_jwt.config;

import lk.spring_security.stateless_jwt.infrastructure.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    //inject jwt auth filter
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    //inject auth provider
    private final AuthenticationProvider authenticationProvider;

    //initiate security filter chain config method
    @Bean
    public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) {

        http
                //disable due to stateless token
                .csrf(AbstractHttpConfigurer::disable)

                //define permission enabled routes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())

                //make full system stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //set custom auth provider
                .authenticationProvider(authenticationProvider)
    }
}
