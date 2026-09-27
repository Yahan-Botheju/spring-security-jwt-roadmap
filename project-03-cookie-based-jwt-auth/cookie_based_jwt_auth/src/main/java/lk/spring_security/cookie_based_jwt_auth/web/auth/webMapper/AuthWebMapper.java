package lk.spring_security.cookie_based_jwt_auth.web.auth.webMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterResult;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.RegisterRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.RegisterResponseDTO;
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
}
