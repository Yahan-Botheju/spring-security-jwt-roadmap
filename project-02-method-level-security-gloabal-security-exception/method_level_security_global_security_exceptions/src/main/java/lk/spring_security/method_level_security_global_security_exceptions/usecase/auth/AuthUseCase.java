package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterResult;

public interface AuthUseCase {

    //register user
    RegisterResult register(RegisterCommand registerCommand);

    //initiate login user
    String loginUser(String email, String password);
}
