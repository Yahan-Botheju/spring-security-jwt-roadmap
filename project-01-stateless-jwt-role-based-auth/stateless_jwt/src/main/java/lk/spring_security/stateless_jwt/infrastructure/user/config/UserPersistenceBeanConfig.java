package lk.spring_security.stateless_jwt.infrastructure.user.config;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.user.UserPersistenceImpl;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.mappers.UserPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPersistenceBeanConfig {
    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
            ) {
        return new UserPersistenceImpl(jpaUserRepository, userPersistenceMapper);
    }
}
