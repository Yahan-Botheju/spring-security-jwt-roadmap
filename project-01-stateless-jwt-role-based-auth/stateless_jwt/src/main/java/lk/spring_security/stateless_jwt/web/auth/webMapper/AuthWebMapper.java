package lk.spring_security.stateless_jwt.web.auth.webMapper;

import lk.spring_security.stateless_jwt.usecase.auth.records.LoginCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginResult;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterResult;
import lk.spring_security.stateless_jwt.web.auth.DTOs.LoginRequestDTO;
import lk.spring_security.stateless_jwt.web.auth.DTOs.LoginResponseDTO;
import lk.spring_security.stateless_jwt.web.auth.DTOs.RegisterRequestDTO;
import lk.spring_security.stateless_jwt.web.auth.DTOs.RegisterResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

    /* __REGISTER__ */

    //request to usecase
    RegisterCommand toRegisterCommand(RegisterRequestDTO registerRequestDTO);

    //domain model to response
    RegisterResponseDTO toRegisterResponseDTO(RegisterResult registerResult);

    /* __LOGIN__ */

    //request to usecase
    LoginCommand toLoginCommand(LoginRequestDTO loginRequestDTO);

    //domain model to response
    LoginResponseDTO toLoginResponseDTO(LoginResult loginResult);
}
