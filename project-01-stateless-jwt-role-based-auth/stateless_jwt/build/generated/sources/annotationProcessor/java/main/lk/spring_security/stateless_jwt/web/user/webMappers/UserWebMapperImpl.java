package lk.spring_security.stateless_jwt.web.user.webMappers;

import javax.annotation.processing.Generated;
import lk.spring_security.stateless_jwt.domain.models.User;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserRequestDTO;
import lk.spring_security.stateless_jwt.web.user.DTOs.UserResponseDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T12:45:00+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.1.jar, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class UserWebMapperImpl implements UserWebMapper {

    @Override
    public User toDomainModel(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userRequestDTO.getEmail() );
        user.password( userRequestDTO.getPassword() );

        return user.build();
    }

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setUserId( user.getUserId() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setRole( user.getRole() );

        return userResponseDTO;
    }
}
