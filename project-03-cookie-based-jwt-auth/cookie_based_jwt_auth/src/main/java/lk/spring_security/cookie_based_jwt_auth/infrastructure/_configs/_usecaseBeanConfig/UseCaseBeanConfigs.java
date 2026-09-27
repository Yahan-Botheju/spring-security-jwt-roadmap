package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.IdentityManager;
import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.services.CookieService;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.RegisterUseCase;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.RegisterUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseBeanConfigs {

    /* __AUTH_USE_CASES__ */

    //register use-case impl
    @Bean
    public RegisterUseCase registerUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            IdentityManager identityManager
    ){
        return new RegisterUseCaseImpl(userRepository, passwordEncoder,cookieService,identityManager);
    }
}
