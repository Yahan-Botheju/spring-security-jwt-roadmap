package lk.spring_security.stateless_jwt.infrastructure.task.config;

import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.TaskPersistenceImpl;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers.TaskPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskPersistenceBeanConfig {
    @Bean
    public TaskRepository taskRepository(
            JpaTaskRepository  jpaTaskRepository,
            TaskPersistenceMapper taskPersistenceMapper
    ) {
        return new TaskPersistenceImpl(jpaTaskRepository, taskPersistenceMapper);
    }
}
