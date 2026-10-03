package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.usecase.auth.records.LoginCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginResult;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterResult;

public interface AuthUseCase {
    //register user
    RegisterResult register(RegisterCommand registerCommand);

    //login user
    LoginResult login(LoginCommand loginCommand);
}
