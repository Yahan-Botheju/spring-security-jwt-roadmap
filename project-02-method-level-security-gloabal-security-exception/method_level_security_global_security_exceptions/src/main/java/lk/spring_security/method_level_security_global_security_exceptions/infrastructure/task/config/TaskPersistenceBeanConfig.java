package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.TaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.TaskPersistenceImpl;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.taskPersistenceMapper.TaskPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskPersistenceBeanConfig {
    @Bean
    public TaskRepository taskRepository(
            JpaTaskRepository taskRepository,
            TaskPersistenceMapper taskPersistenceMapper
    ) {
        return new TaskPersistenceImpl(taskRepository, taskPersistenceMapper);
    }
}
