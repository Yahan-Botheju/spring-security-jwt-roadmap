package lk.spring_security.stateless_jwt.infrastructure.task.config;

import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.usecase.task.TaskUseCase;
import lk.spring_security.stateless_jwt.usecase.task.TaskUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskUseCaseBeanConfig {
    @Bean
    public TaskUseCase taskUseCase(
            TaskRepository taskRepository
    ) {
        return new TaskUseCaseImpl(taskRepository);
    }
}
