package lk.spring_security.stateless_jwt.infrastructure.configs;

import lk.spring_security.stateless_jwt.domain.repositories.TaskRepository;
import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.TaskPersistenceImpl;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.jpa.JpaTaskRepository;
import lk.spring_security.stateless_jwt.infrastructure.task.persistence.mappers.TaskPersistenceMapper;
import lk.spring_security.stateless_jwt.infrastructure.user.UserPersistenceImpl;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.mappers.UserPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceBeanConfigs {

    //user persistence impl
    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        return new UserPersistenceImpl(jpaUserRepository, userPersistenceMapper);
    }

    //task persistence impl
    @Bean
    public TaskRepository taskRepository(
            JpaTaskRepository jpaTaskRepository,
            TaskPersistenceMapper taskPersistenceMapper,
            JpaUserRepository jpaUserRepository
    ) {
        return new TaskPersistenceImpl(jpaTaskRepository, taskPersistenceMapper, jpaUserRepository);
    }
}
