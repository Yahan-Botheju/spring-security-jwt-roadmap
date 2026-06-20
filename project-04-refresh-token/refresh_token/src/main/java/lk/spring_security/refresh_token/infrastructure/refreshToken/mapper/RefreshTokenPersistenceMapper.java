package lk.spring_security.refresh_token.infrastructure.user.mappers;

import lk.spring_security.refresh_token.domain.models.RefreshToken;
import lk.spring_security.refresh_token.infrastructure.user.entities.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class})
public interface RefreshTokenPersistenceMapper {

    //domain model to entity
    RefreshTokenEntity toEntity(RefreshToken refreshToken);

    //entity to domain model
    RefreshToken toDomainModel(RefreshTokenEntity refreshTokenEntity);

}
