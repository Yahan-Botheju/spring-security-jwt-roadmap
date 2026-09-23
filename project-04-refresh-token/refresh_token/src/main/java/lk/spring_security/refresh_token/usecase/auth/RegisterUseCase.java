package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.usecase.auth.records.RegisterCommand;
import lk.spring_security.refresh_token.usecase.auth.records.RegisterResult;

public interface RegisterUseCase {
    //register user
    RegisterResult register(RegisterCommand  registerCommand);
}
