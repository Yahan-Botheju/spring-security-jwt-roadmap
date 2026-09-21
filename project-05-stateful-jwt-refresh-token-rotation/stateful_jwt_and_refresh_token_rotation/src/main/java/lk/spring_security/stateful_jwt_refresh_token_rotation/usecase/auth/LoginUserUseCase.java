package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.LoginUserCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.records.LoginUserResult;

public interface LoginUserUseCase {

    //login user
    LoginUserResult loginUser(LoginUserCommand loginUserCommand);
}
