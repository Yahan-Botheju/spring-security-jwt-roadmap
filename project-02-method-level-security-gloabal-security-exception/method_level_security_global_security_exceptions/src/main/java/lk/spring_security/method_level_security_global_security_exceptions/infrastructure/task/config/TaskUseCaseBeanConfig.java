package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.task.TaskUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskUseCaseBeanConfig {
    @Bean
    public TaskUseCase taskUseCase(
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        return new TaskUseCaseImpl(taskRepository, userRepository);
    }
}
