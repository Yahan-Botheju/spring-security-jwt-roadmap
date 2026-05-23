package lk.spring_security.stateless_jwt.web.user.webMappers;

import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserRequestDTO;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserWebMapper {
    //requestDTO to domain model
    User toDomainModel(UserRequestDTO userRequestDTO);

    //domain model to responseDTO
    UserResponseDTO toResponseDTO(User user);
}
