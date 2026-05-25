package lk.spring_security.stateless_jwt.infrastructure.task.config;

import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.TaskPersistenceImpl;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers.TaskPersistenceMapper;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.jpa.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskPersistenceBeanConfig {
    @Bean
    public TaskRepository taskRepository(
            JpaTaskRepository  jpaTaskRepository,
            TaskPersistenceMapper taskPersistenceMapper,
            JpaUserRepository jpaUserRepository
    ) {
        return new TaskPersistenceImpl(jpaTaskRepository, taskPersistenceMapper, jpaUserRepository);
    }
}
