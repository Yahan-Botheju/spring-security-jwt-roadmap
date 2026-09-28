package lk.spring_security.cookie_based_jwt_auth.web.user.webMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsResult;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UpdateUserRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UpdateUserResponseDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UserRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    //domain model to responseDTO
    UserResponseDTO toResponseDTO(User user);

    //requestDTO to domain model
    User toDomainModel(UserRequestDTO userRequestDTO);

    /* __UPDATE_USER__ */

    //requestDTO to usecase command
    UpdateUserDetailsCommand toUpdateUserDetailsCommand(Long userId, UpdateUserRequestDTO updateUserRequestDTO);

    //domain model to responseDTO
    UpdateUserResponseDTO toUpdateUserResponseDTO(UpdateUserDetailsResult updateUserDetailsResult);
}
