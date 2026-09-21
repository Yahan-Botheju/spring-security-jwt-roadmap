package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.RegisterUseCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.RegisterUserResult;

public interface RegisterUserUseCase {

    //register user
    RegisterUserResult registerUser(RegisterUseCommand registerUseCommand);
}
