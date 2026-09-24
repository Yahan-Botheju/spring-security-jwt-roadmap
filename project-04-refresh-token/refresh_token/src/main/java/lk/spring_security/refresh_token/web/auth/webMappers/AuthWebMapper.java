package lk.spring_security.refresh_token.web.auth.webMappers;

import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterResult;
import lk.spring_security.refresh_token.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.refresh_token.web.auth.DTOs.AuthResponseDTO;
import lk.spring_security.refresh_token.web.auth.DTOs.RegisterResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {
    //requestDTO to domain model
    User toDomainModel(AuthRequestDTO authRequestDTO);

    //domain model to responseDTO
    AuthResponseDTO toResponseDTO(User user);

    /* __REGISTER_USE__ */

    //requestDTO to usecase
    RegisterCommand toRegisterCommand(RegisterCommand registerCommand);

    //domain model to usecase
    RegisterResponseDTO toRegisterResponseDTO(RegisterResult registerResult);
}
