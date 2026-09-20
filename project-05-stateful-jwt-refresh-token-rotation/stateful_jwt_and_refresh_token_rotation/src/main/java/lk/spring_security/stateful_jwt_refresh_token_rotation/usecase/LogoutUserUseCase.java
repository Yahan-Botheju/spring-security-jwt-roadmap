package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.LogoutUserCommand;

public interface LogoutUserUseCase {
    //logout user
    void logout(LogoutUserCommand logoutUserCommand);
}
