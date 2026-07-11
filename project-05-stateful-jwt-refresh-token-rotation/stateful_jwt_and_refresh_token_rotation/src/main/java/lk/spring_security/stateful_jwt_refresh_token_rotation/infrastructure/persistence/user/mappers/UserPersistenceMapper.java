package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.mappers;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure.persistence.user.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    //domain model to entity
    UserEntity toEntity(User user);

    //entity to domain model
    User toDomainModel(UserEntity userEntity);

    //update
    UserEntity updateEntity(User user, @MappingTarget UserEntity userEntity);
}
