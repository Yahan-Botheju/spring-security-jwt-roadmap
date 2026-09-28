package lk.spring_security.cookie_based_jwt_auth.web.auth.webMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.LoginCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.LoginResult;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterResult;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

    //requestDTO to domain model
    User toDomainModel(AuthRequestDTO authRequestDTO);

    /* __REGISTER__ */

    //requestDTO to usecase
    RegisterCommand toRegisterCommand(RegisterRequestDTO registerRequestDTO);

    //domain model to responseDTO
    RegisterResponseDTO  toRegisterResponseDTO(RegisterResult registerResult);


    /* __REGISTER__ */

    //requestDTO to usecase
    LoginCommand  toLoginCommand(LoginRequestDTO loginRequestDTO);

    //domain model to responseDTO
    LoginResponseDTO toLoginResponseDTO(LoginResult loginResult);
}
