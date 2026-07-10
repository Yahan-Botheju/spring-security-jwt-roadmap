package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._persistenceBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenReposiroty;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.RefreshTokenRepositoryImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.jpa.JpaRefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.mappers.RefreshTokenPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RefreshTokenPersistenceBeanConfig {
    @Bean
    public RefreshTokenReposiroty refreshTokenReposiroty(
            JpaRefreshTokenRepository jpaRefreshTokenRepository,
            RefreshTokenPersistenceMapper refreshTokenPersistenceMapper,
            UserRepository userRepository
    ){
        return new RefreshTokenRepositoryImpl(
                jpaRefreshTokenRepository,
                refreshTokenPersistenceMapper,
                userRepository
        );
    }
}
