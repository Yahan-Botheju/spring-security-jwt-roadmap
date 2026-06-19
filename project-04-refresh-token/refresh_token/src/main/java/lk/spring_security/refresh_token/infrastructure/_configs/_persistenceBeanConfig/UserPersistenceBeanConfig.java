package lk.spring_security.refresh_token.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.infrastructure.user.UserRepositoryImpl;
import lk.spring_security.refresh_token.infrastructure.user.jpa.JpaUserRepository;
import lk.spring_security.refresh_token.infrastructure.user.mappers.UserPersistenceMapper;
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
