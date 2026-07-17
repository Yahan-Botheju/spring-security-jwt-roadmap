package lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.webMapper;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthResult;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs.AuthResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {
    //requestDTO to domain model
    User toDomainModel(AuthRequestDTO authRequestDTO);

    //domain model to responseDTO
    AuthResponseDTO toResponse(AuthResult authResult);
}
