package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;

public interface AuthUseCase {

    //register user
    void registerUser(User user);
}
