package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.usecase.auth.records.LoginCommand;
import lk.spring_security.refresh_token.usecase.auth.records.LoginResult;

public interface LoginUseCase {

    //login
    LoginResult login(LoginCommand loginCommand);
}
