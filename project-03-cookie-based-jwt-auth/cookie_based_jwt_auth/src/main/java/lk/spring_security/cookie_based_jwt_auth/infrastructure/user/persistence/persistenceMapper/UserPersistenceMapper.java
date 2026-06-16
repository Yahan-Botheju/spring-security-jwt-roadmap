package lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.persistenceMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.infrastructure.user.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    //domain model to entity
    UserEntity toEntity(User user);

    //entity to domain model
    User toDomainModel(UserEntity userEntity);

    //update mapper
    UserEntity updateEntity(User user, @MappingTarget UserEntity userEntity);
}
