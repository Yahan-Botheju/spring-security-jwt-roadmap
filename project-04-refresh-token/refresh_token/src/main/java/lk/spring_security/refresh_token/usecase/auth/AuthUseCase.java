package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.models.User;

public interface AuthUseCase {

    //register new user
    User registerUser(User user);
}
