package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.auth.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security.JwtImpl;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.AuthUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.AuthUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthBeanConfig {
    @Bean
    public AuthUseCase authUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtImpl jwtImpl,
            AuthenticationManager authenticationManager
    ){
        return new AuthUseCaseImpl(userRepository,passwordEncoder,jwtImpl,authenticationManager);
    }
}
