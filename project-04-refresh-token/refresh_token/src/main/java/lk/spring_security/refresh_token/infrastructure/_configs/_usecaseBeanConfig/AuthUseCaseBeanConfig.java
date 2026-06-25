package lk.spring_security.refresh_token.infrastructure._configs._usecaseBeanConfig;

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
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            AuthenticationManager authenticationManager
    ) {
        return new AuthUseCaseImpl(userRepository,passwordEncoder, cookieService, authenticationManager);
    }
}
