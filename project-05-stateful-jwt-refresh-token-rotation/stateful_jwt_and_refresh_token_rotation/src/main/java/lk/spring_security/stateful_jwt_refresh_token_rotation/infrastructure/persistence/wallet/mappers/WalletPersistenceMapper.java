package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.mappers;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.Wallet;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.wallet.entities.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletPersistenceMapper {
    //domain model to entity
    WalletEntity toEntity(Wallet wallet);

    //entity to domain model
    Wallet toDomainModel(WalletEntity walletEntity);
}
