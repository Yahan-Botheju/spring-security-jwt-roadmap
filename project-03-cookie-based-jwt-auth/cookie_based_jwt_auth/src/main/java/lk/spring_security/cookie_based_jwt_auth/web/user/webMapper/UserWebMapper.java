package lk.spring_security.cookie_based_jwt_auth.web.user.webMapper;

import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.user.records.UpdateUserDetailsResult;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UpdateUserRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.user.DTOs.UpdateUserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {

    /* __UPDATE_USER__ */

    //requestDTO to usecase command
    UpdateUserDetailsCommand toUpdateUserDetailsCommand(Long userId, UpdateUserRequestDTO updateUserRequestDTO);

    //domain model to responseDTO
    UpdateUserResponseDTO toUpdateUserResponseDTO(UpdateUserDetailsResult updateUserDetailsResult);
}
