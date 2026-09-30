package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginResult;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterResult;

public interface AuthUseCase {

    //register user
    RegisterResult register(RegisterCommand registerCommand);

    //login user
    LoginResult login(LoginCommand loginCommand);
}
