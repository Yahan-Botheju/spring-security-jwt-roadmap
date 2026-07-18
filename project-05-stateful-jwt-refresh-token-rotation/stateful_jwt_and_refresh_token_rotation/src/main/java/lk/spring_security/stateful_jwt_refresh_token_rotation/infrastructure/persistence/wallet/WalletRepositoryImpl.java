package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.WalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.entities.WalletEntity;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.jpa.JpaWalletRepository;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.mappers.WalletPersistenceMapper;

import java.util.Optional;

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

    //find wallet by user email
    @Override
    public Optional<Wallet> findByUserEmail(String email) {
        return jpaWalletRepository.findByUser_Email(email).map(walletPersistenceMapper::toDomainModel);
    }

    //save wallet
    @Override
    public Wallet saveWallet(Wallet wallet) {
        if (jpaWalletRepository.existsById(wallet.getWalletId())) {
            throw new RuntimeException("wallet already exists");
        }
        WalletEntity toEntity = walletPersistenceMapper.toEntity(wallet);
        WalletEntity savedEntity = jpaWalletRepository.save(toEntity);

        return walletPersistenceMapper.toDomainModel(savedEntity);
    }


}
