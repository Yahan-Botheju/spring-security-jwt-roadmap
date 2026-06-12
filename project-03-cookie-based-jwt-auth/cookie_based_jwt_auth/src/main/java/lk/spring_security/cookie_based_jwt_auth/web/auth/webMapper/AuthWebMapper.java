package lk.spring_security.cookie_based_jwt_auth.web.auth.webMapper;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.AuthRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

    //requestDTO to domain model
    User toDomainModel(AuthRequestDTO authRequestDTO);
}
