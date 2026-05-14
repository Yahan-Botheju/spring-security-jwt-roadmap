package lk.spring_security.stateless_jwt.infrastructure.user.persistence.mappers;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.infrastructure.user.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    //domain model to entity
    UserEntity toEntity(User user);

    //entity to domain model
    User toDomainModel(UserEntity userEntity);
}
