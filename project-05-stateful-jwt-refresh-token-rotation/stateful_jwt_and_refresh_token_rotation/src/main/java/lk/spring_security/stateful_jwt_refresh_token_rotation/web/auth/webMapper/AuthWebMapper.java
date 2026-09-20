package lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.webMapper;


import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthWebMapper {

    /* __REFRESH_TOKEN__ */

    //domain model to responseDTO
    RefreshTokenResponseDTO toRefreshTokenResponse(RefreshTokenResult refreshTokenResult);


    /* __REGISTER_USER__ */

    //requestDTO to usecase obj
    RegisterUseCommand toRegisterUserCommand(RegisterUserRequestDTO registerUserRequestDTO);

    //domain model to responseDTO
    RegisterUserResponseDTO toRegisterUserResponse(RegisterUserResult registerUserResult);



    /* __LOGIN_USER__ */

    //requestDTO to usecase obj
    LoginUserCommand  toLoginUserCommand(LoginUserRequestDTO loginUserRequestDTO);

    //domain model to responseDTO
    LoginUserResponseDTO toLoginUserResponse(LoginUserResult loginUserResult);


}
