package lk.spring_security.method_level_security_global_security_exceptions.web.user.webMappers;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.user.records.UpdateUserResult;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UpdateUserRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UpdateUserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {

    /* __UPDATE_USER_USE_CASE__ */

    //request to command
    UpdateUserCommand toUpdateUserCommand(Long userId, UpdateUserRequestDTO updateUserRequestDTO);

    //domain model to response
    UpdateUserResponseDTO toUpdateUserResponseDTO(UpdateUserResult updateUserResult);
}
