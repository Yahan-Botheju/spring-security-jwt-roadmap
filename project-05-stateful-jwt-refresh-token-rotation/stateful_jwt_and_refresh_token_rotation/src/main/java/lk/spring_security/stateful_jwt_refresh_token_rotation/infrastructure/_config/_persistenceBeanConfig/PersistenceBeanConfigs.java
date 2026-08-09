package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._persistenceBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.IdentityProvider;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.identity_provider.IdentityProviderImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.RefreshTokenRepositoryImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.jpa.JpaRefreshTokenRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.mappers.RefreshTokenPersistenceMapper;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.UserRepositoryImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.jpa.JpaUserRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.mappers.UserPersistenceMapper;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.WalletRepositoryImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.jpa.JpaWalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.mappers.WalletPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PersistenceBeanConfigs {

    //refresh token repository impl
    @Bean
    public RefreshTokenRepository refreshTokenRepository(
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

    //user repository impl
    @Bean
    public UserRepository userRepository(
            JpaUserRepository jpaUserRepository,
            UserPersistenceMapper userPersistenceMapper
    ){
        return new UserRepositoryImpl(jpaUserRepository, userPersistenceMapper);
    }

    //wallet repository impl
    @Bean
    public WalletRepository walletRepository(
            JpaWalletRepository jpaWalletRepository,
            WalletPersistenceMapper walletPersistenceMapper
    ){
        return new WalletRepositoryImpl(jpaWalletRepository,walletPersistenceMapper);
    }

    //identity provider
    @Bean
    public IdentityProvider identityProvider(
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ){
        return new IdentityProviderImpl(passwordEncoder, authenticationManager);
    }
}
