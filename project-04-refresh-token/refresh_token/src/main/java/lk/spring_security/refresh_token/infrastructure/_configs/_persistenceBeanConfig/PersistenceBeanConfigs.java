package lk.spring_security.refresh_token.infrastructure._configs._persistenceBeanConfig;

import lk.spring_security.refresh_token.domain.repositories.ProductRepository;
import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.infrastructure.product.ProductRepositoryImpl;
import lk.spring_security.refresh_token.infrastructure.product.jpa.JpaProductRepository;
import lk.spring_security.refresh_token.infrastructure.product.mappers.ProductPersistenceMapper;
import lk.spring_security.refresh_token.infrastructure.refreshToken.RefreshTokenRepositoryImpl;
import lk.spring_security.refresh_token.infrastructure.refreshToken.jpa.JpaRefreshTokenRepository;
import lk.spring_security.refresh_token.infrastructure.refreshToken.mapper.RefreshTokenPersistenceMapper;
import lk.spring_security.refresh_token.infrastructure.user.UserRepositoryImpl;
import lk.spring_security.refresh_token.infrastructure.user.jpa.JpaUserRepository;
import lk.spring_security.refresh_token.infrastructure.user.mappers.UserPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceBeanConfigs {

    //product persistence impl
    @Bean
    public ProductRepository productRepository(
            JpaProductRepository jpaProductRepository,
            ProductPersistenceMapper productPersistenceMapper
    ) {
        return new ProductRepositoryImpl(jpaProductRepository, productPersistenceMapper);
    }

    //refresh token persistence impl
    @Bean
    public RefreshTokenRepository refreshTokenRepository(
            JpaRefreshTokenRepository jpaRefreshTokenRepository,
            RefreshTokenPersistenceMapper refreshTokenPersistenceMapper,
            UserRepository userRepository
    ) {
        return new RefreshTokenRepositoryImpl(jpaRefreshTokenRepository, refreshTokenPersistenceMapper, userRepository);
    }

    //user persistence impl
    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ) {
        return new UserRepositoryImpl(jpaUserRepository, userPersistenceMapper);
    }
}
