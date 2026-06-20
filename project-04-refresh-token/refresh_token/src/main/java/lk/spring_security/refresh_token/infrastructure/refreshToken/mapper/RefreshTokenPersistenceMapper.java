package lk.spring_security.refresh_token.infrastructure.refreshToken.mapper;

import lk.spring_security.refresh_token.domain.models.RefreshToken;
import lk.spring_security.refresh_token.infrastructure.refreshToken.entities.RefreshTokenEntity;
import lk.spring_security.refresh_token.infrastructure.user.mappers.UserPersistenceMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class})
public interface RefreshTokenPersistenceMapper {

    //domain model to entity
    RefreshTokenEntity toEntity(RefreshToken refreshToken);

    //entity to domain model
    RefreshToken toDomainModel(RefreshTokenEntity refreshTokenEntity);

}
