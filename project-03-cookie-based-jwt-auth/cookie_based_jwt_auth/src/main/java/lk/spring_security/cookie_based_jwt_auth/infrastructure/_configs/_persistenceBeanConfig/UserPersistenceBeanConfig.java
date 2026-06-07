package lk.spring_security.cookie_based_jwt_auth.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.UserRepositoryImpl;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.persistenceMapper.UserPersistenceMapper;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.jpa.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPersistenceBeanConfig {
    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        return new UserRepositoryImpl(jpaUserRepository, userPersistenceMapper);
    }
}
