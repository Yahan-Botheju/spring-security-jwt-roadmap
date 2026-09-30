package lk.spring_security.method_level_security_global_security_exceptions.web.auth.webMappers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginResult;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterResult;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {
    //requestDTO to domain model
    User authToDomainModel(AuthRequestDTO authRequestDTO);

    //domain model to responseDTO
    default AuthResponseDTO authResponse(String token){
        return new AuthResponseDTO(token);
    }

    /* __LOGIN__ */

    //request to command
    LoginCommand toLoginCommand(LoginRequestDTO loginRequestDTO);

    //domain model to response
    LoginResponseDTO toLoginResponseDTO(LoginResult loginResult);


    /* __REGISTER__ */

    //request to command
    RegisterCommand  toRegisterCommand(RegisterRequestDTO registerRequestDTO);

    //domain model to response
    RegisterResponseDTO toRegisterResponseDTO(RegisterResult registerResult);
}
