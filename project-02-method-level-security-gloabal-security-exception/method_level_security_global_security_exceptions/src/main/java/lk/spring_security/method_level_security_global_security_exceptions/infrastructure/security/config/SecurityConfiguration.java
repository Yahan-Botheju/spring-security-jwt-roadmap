package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config;

import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.config.exception.JwtAuthenticationEntryPoint;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    //inject required classes and spring classes
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    //initiate security filter chain config method
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) {
        http
                //disable csrf because of stateless token
                .csrf(AbstractHttpConfigurer::disable)

                //define permission enable routes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v2/auth/**").permitAll()
                        .requestMatchers("/api/v2/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())

                //make full system stateless
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //set custom auth provider
                .authenticationProvider(authenticationProvider)

                //run jwt filter before login to filter
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
    }
}
