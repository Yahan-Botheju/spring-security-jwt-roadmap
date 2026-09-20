package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RegisterUseCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.RegisterUserResult;

public interface RegisterUserUseCase {

    //register user
    RegisterUserResult registerUser(RegisterUseCommand registerUseCommand);
}
