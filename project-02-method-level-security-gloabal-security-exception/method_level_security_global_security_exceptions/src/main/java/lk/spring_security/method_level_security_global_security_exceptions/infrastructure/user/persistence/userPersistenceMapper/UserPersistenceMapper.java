package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.userPersistenceMapper;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.task.persistence.entity.TaskEntity;
import lk.spring_security.method_level_security_global_security_exceptions.infrastructure.user.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    //domain model to entity
    UserEntity toEntity(User user);

    //entity to domain model
    User toDomainModel(UserEntity userEntity);

    //update user
    UserEntity updateUser(User user, @MappingTarget UserEntity userEntity);
}
