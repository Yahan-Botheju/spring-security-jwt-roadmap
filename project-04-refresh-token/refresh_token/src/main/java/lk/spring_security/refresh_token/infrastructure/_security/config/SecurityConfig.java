package lk.spring_security.refresh_token.infrastructure._security.config;

import lk.spring_security.refresh_token.infrastructure._security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
public class SecurityConfig {

    //inject required dependencies
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    //initiate spring security filter chain config method
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //disable due to dev env
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        //auth routes
                        .requestMatchers("/api/v1/auth/**").permitAll() //permits auth endpoints
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") //permits for admin routes

                        //products routes
                        .requestMatchers(HttpMethod.POST,"/api/v1/products/**").hasRole("ADMIN") //only ADMIN can create products
                        .requestMatchers(HttpMethod.GET,"/api/v1/products/**").hasAnyRole("ADMIN","USER") //both can view products
                        .anyRequest().authenticated())

                //set session as stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                //initiate auth provider and cookie filter
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}

