package lk.spring_security.stateless_jwt.web.auth.webMapper;

import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterResult;
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
}
