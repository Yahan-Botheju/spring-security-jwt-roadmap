package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._usecaseBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.UserUseCase;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.UserUseCaseImpl;
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
