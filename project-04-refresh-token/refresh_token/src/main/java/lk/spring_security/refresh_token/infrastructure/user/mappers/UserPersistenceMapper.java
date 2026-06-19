package lk.spring_security.refresh_token.infrastructure.user.mappers;

import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.infrastructure.user.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    //domain model to entity
    UserEntity toEntity(User user);

    //entity to domain model
    User toDomainMode(UserEntity userEntity);
}
