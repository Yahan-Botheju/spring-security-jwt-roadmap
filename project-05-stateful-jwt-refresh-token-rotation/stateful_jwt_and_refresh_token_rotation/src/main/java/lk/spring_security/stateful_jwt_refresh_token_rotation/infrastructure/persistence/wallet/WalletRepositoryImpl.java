package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.jpa.JpaWalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.mappers.WalletPersistenceMapper;

public class WalletRepositoryImpl implements WalletRepository {

    //inject required dependencies
    private final JpaWalletRepository jpaWalletRepository;
    private final WalletPersistenceMapper walletPersistenceMapper;

    public WalletRepositoryImpl(
            JpaWalletRepository jpaWalletRepository,
            WalletPersistenceMapper walletPersistenceMapper
    ) {
        this.jpaWalletRepository = jpaWalletRepository;
        this.walletPersistenceMapper = walletPersistenceMapper;
    }
}
