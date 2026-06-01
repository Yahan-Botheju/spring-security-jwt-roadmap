package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.config;

import lk.spring_security.method_level_security_global_security_exceptions.domain.repositories.UserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.UserPersistenceImpl;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.jpa.JpaUserRepository;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.userPersistenceMapper.UserPersistenceMapper;
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
