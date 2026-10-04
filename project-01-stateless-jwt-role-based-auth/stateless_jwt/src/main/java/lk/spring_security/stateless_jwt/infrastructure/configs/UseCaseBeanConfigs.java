package lk.spring_security.stateless_jwt.infrastructure.configs;

import lk.spring_security.stateless_jwt.domain.repositories.IdentityManger;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.domain.repositories.JwtService;
import lk.spring_security.stateless_jwt.infrastructure.security.IdentityManagerImpl;
import lk.spring_security.stateless_jwt.usecase.auth.AuthUseCase;
import lk.spring_security.stateless_jwt.usecase.auth.AuthUseCaseImpl;
import lk.spring_security.stateless_jwt.usecase.user.UserUseCase;
import lk.spring_security.stateless_jwt.usecase.user.UserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseBeanConfigs {

    //identity manager
    @Bean
    public IdentityManger identityManger(
            AuthenticationManager authenticationManager
    ) {
        return new IdentityManagerImpl(authenticationManager);
    }

    //auth use case impl
    @Bean
    public AuthUseCase  authUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            IdentityManger identityManger
    ){
        return new AuthUseCaseImpl(userRepository, passwordEncoder, jwtService, identityManger);
    }

    //user use case impl
    @Bean
    public UserUseCase userUseCase(
            UserRepository userRepository
    ) {
        return new UserUseCaseImpl(userRepository);
    }
}
