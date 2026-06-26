package lk.spring_security.refresh_token.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.usecase.auth.AuthUseCase;
import lk.spring_security.refresh_token.usecase.auth.AuthUseCaseImpl;
import lk.spring_security.refresh_token.web._shared.services.CookieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthUseCaseBeanConfig {
    @Bean
    public AuthUseCase authUseCase(
            RefreshTokenRepository refreshTokenRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            TokenService tokenService,
            AuthenticationManager authenticationManager
    ) {
        return new AuthUseCaseImpl(refreshTokenRepository ,userRepository,passwordEncoder, cookieService,tokenService ,authenticationManager);
    }
}
