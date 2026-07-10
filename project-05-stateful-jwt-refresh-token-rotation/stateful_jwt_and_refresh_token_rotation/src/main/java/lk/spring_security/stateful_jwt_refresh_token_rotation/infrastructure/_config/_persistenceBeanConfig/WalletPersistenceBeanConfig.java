package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._config._persistenceBeanConfig;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.WalletRepositoryImpl;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.jpa.JpaWalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.mappers.WalletPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletPersistenceBeanConfig {
    @Bean
    public WalletRepository walletRepository(
            JpaWalletRepository jpaWalletRepository,
            WalletPersistenceMapper walletPersistenceMapper
    ){
        return new WalletRepositoryImpl(jpaWalletRepository,walletPersistenceMapper);
    }
}
