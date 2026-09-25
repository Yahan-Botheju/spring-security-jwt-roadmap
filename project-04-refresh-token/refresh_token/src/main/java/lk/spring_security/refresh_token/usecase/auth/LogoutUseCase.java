package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.usecase.auth.records.LogoutCommand;
import lk.spring_security.refresh_token.usecase.auth.records.LogoutResult;

public interface LogoutUseCase {
    //logout
    LogoutResult logout(LogoutCommand logoutCommand);
}
