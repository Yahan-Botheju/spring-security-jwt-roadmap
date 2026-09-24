package lk.spring_security.refresh_token.infrastructure._configs._jwtBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.CookieService;
import lk.spring_security.refresh_token.domain.repositories.IdentityProvider;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.infrastructure._security.IdentityProviderImpl;
import lk.spring_security.refresh_token.infrastructure._security.JwtImpl;
import lk.spring_security.refresh_token.infrastructure._security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.crypto.SecretKey;

@Configuration
public class JwtImplBeanConfig {
    //token service
    @Bean
    public TokenService tokenService(
            SecretKey secretKey,
            @Value("${application.security.cookie.access-token-expiry-seconds}") long  accessTokenExpirySeconds
    ) {
        long expirationMs = accessTokenExpirySeconds * 1000L;

        return new JwtImpl(secretKey, expirationMs);
    }

    //auth filter
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            CookieService cookieService,
            TokenService tokenService,
            UserDetailsService userDetailsService
    ) {
        return new JwtAuthenticationFilter(cookieService, tokenService, userDetailsService);
    }

    //identity provider
    @Bean
    public IdentityProvider  identityProvider(AuthenticationManager authenticationManager) {
        return new IdentityProviderImpl(authenticationManager);
    }
}
