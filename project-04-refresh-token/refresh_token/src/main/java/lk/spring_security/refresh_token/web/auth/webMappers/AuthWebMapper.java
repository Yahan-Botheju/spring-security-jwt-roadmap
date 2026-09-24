package lk.spring_security.refresh_token.web.auth.webMappers;

import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.usecase.auth.records.LoginCommand;
import lk.spring_security.refresh_token.usecase.auth.records.LoginResult;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterResult;
import lk.spring_security.refresh_token.web.auth.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {
    //requestDTO to domain model
    User toDomainModel(AuthRequestDTO authRequestDTO);

    //domain model to responseDTO
    AuthResponseDTO toResponseDTO(User user);

    /* __REGISTER_USE_CASE__ */

    //requestDTO to usecase
    RegisterCommand toRegisterCommand(RegisterRequestDTO registerRequestDTO);

    //domain model to usecase
    RegisterResponseDTO toRegisterResponseDTO(RegisterResult registerResult);


    /* __LOGIN_USE_CASE__*/

    //requestDTO to usecase
    LoginCommand toLoginCommand(LoginRequestDTO loginRequestDTO);

    //domain model to responseDTO
    LoginResponseSTO toLoginResponseDTO(LoginResult loginResult);
}
