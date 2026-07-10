package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._persistenceBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.UserRepositoryImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.jpa.JpaUserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.mappers.UserPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPersistenceBeanConfig {
    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ){
        return new UserRepositoryImpl(jpaUserRepository, userPersistenceMapper);
    }
}
