package lk.spring_security.method_level_security_global_security_exceptions.web.user.webMappers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserResult;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UpdateUserRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UpdateUserResponseDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    //requestDTO to domain model
    User toDomainModel(UserRequestDTO userRequestDTO);

    //domain model to responseDTO
    UserResponseDTO toResponseDTO(User user);

    /* __UPDATE_USER_USE_CASE__ */

    //request to command
    UpdateUserCommand toUpdateUserCommand(Long userId, UpdateUserRequestDTO updateUserRequestDTO);

    //domain model to response
    UpdateUserResponseDTO toUpdateUserResponseDTO(UpdateUserResult updateUserResult);
}
