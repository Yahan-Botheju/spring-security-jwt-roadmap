package lk.spring_security.method_level_security_global_security_exceptions.web.user.webMappers;

import lk.spring_security.method_level_security_global_security_exceptions.domain.models.User;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.user.DTOs.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    //requestDTO to domain model
    User toDomainModel(UserRequestDTO userRequestDTO);

    //domain model to responseDTO
    UserResponseDTO toResponseDTO(User user);
}
