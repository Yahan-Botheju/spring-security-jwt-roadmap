package lk.spring_security.refresh_token.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.infrastructure.refreshToken.RefreshTokenRepositoryImpl;
import lk.spring_security.refresh_token.infrastructure.refreshToken.jpa.JpaRefreshTokenRepository;
import lk.spring_security.refresh_token.infrastructure.refreshToken.mapper.RefreshTokenPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RefreshTokenPersistenceBeanConfig {
    @Bean
    public RefreshTokenRepository refreshTokenRepository(
            JpaRefreshTokenRepository jpaRefreshTokenRepository,
            RefreshTokenPersistenceMapper refreshTokenPersistenceMapper,
            UserRepository userRepository
    ) {
        return new RefreshTokenRepositoryImpl(jpaRefreshTokenRepository, refreshTokenPersistenceMapper, userRepository);
    }
}
