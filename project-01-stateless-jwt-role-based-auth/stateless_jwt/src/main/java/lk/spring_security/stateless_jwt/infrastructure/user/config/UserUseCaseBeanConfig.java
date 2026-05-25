package lk.spring_security.stateless_jwt.infrastructure.user.config;

import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.usecase.user.UserUseCase;
import lk.spring_security.stateless_jwt.usecase.user.UserUseCaseImpl;
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
