package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.services.CookieService;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.LoginUseCase;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.LoginUseCaseImpl;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.RegisterUseCase;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.RegisterUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class UseCaseBeanConfigs {

    /* __AUTH_USE_CASES__ */

    //register use-case impl
    @Bean
    public RegisterUseCase registerUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService
    ){
        return new RegisterUseCaseImpl(userRepository, passwordEncoder,cookieService);
    }

    //login use-case impl
    @Bean
    public LoginUseCase  loginUseCase(
            UserRepository userRepository,
            CookieService cookieService,
            AuthenticationManager authenticationManager
    ){
        return new LoginUseCaseImpl(userRepository,cookieService,authenticationManager);
    }
}
