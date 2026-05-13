package lk.spring_security.stateless_jwt.infrastructure.config;

import lk.spring_security.stateless_jwt.domain.repositories.UserRepository;
import lk.spring_security.stateless_jwt.infrastructure.persistence.UserPersistenceImpl;
import lk.spring_security.stateless_jwt.infrastructure.persistence.jpa.JpaUserRepository;
import lk.spring_security.stateless_jwt.infrastructure.persistence.mappers.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PersistenceBeanConfig {
    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
            ) {
        return new UserPersistenceImpl(jpaUserRepository, userPersistenceMapper);
    }
}
