package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.AuthUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.AuthUseCaseImpl;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCaseImpl;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.UserUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.UserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseBeanConfigs {

    @Bean
    public AuthUseCase authUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ){
        return new AuthUseCaseImpl(userRepository,passwordEncoder,jwtService,authenticationManager);
    }

    @Bean
    public UserUseCase userUseCase(
            UserRepository userRepository
    ) {
        return new UserUseCaseImpl(userRepository);
    }

    @Bean
    public TaskUseCase taskUseCase(
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        return new TaskUseCaseImpl(taskRepository, userRepository);
    }
}
