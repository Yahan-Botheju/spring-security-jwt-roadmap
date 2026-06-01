package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.UserUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.UserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseBeanConfig {
    @Bean
    public UserUseCase userUseCase(
            UserRepository userRepository
    ) {
        return new UserUseCaseImpl(userRepository);
    }
}
