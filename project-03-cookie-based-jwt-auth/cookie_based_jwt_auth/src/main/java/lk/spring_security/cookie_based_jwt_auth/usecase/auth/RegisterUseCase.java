package lk.spring_security.cookie_based_jwt_auth.usecase.auth;

import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterCommand;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.records.RegisterResult;

public interface RegisterUseCase {

    //register user
    RegisterResult register(RegisterCommand registerCommand);
}
