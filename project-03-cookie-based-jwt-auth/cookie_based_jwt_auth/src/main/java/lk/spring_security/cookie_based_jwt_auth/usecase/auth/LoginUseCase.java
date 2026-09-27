package lk.spring_security.cookie_based_jwt_auth.usecase.auth;

import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.LoginCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.LoginResult;

public interface LoginUseCase {
    //login user
    LoginResult login(LoginCommand loginCommand);
}
