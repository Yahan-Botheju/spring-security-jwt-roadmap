package lk.spring_security.method_level_security_global_security_exceptions.web.auth.webMappers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {
    //requestDTO to domain model
    User authToDomainModel(AuthRequestDTO authRequestDTO);

    //domain model to responseDTO
    AuthResponseDTO toAuthResponseDTO(User user);
}
