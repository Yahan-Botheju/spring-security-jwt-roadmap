package lk.spring_security.cookie_based_jwt_auth.infrastructure._security.config;

import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //inject required classes as constructor injection
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private  final AuthenticationEntryPoint unauthorizedEntryPoint;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationEntryPoint authenticationEntryPoint,
            AuthenticationEntryPoint unauthorizedEntryPoint
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
    }

    //initiate spring security config method
    @Bean
    public SecurityFilterChain springSecurityFilterChain(
            HttpSecurity http
    ) {
        http

                //CSRF attack preventing (DISABLE due to learning project)
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/auth/**").permitAll() //allow register, login, logout as public
                        .requestMatchers("/api/v1/admin/**").hasAuthority("ROLE_ADMIN") //initiate admin routes
                        .anyRequest().authenticated()
                )
    }


}

