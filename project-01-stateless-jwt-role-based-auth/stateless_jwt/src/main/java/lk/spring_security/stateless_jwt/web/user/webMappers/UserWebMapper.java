package lk.spring_security.stateless_jwt.web.user.webMappers;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.usecase.user.records.GetUserProfileCommand;
import lk.spring_security.stateless_jwt.usecase.user.records.GetUserProfileResult;
import lk.spring_security.stateless_jwt.usecase.user.records.UpdateUserProfileCommand;
import lk.spring_security.stateless_jwt.usecase.user.records.UpdateUserProfileResult;
import lk.spring_security.stateless_jwt.web.user.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    //requestDTO to domain model
    User toDomainModel(UserRequestDTO userRequestDTO);

    //domain model to responseDTO
    UserResponseDTO toResponseDTO(User user);

    /* __GET_USER_PROFILE__ */

    //request to command
    GetUserProfileCommand toGetUserProfileCommand(String email);

    //domain model to response
    GetUserProfileResponseDTO toGetUserProfileResponseDTO(GetUserProfileResult getUserProfileResult);

    /* __UPDATE_USER_PROFILE__ */

    //request to command
    UpdateUserProfileCommand toUpdateUserProfileCommand(String currentEmail, UpdateUserProfileRequestDTO updateUserProfileRequestDTO);

    //domain model to response
    UpdateUserProfileResponseDTO toUpdateUserProfileResponseDTO(UpdateUserProfileResult updateUserProfileResult);
}
