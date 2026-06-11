package lk.spring_security.cookie_based_jwt_auth.usecase.auth;

import lk.spring_security.cookie_based_jwt_auth.domain.models.User;

public interface AuthUseCase {
    //register new user
    String registerUser(User user);

    //login user
    String loginUser(String email, String password);
}
