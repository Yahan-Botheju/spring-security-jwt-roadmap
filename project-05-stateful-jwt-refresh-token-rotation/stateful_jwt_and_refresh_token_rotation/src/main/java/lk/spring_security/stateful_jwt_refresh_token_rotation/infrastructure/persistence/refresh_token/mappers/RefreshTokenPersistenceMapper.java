package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.mappers;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.RefreshToken;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.refresh_token.entities.RefreshTokenEntity;

public interface RefreshTokenPersistenceMapper {
    //domain model to entity
    RefreshTokenEntity toEntity(RefreshToken refreshToken);

    //entity to domain model
    RefreshToken toDomainModel(RefreshTokenEntity refreshTokenEntity);
}
