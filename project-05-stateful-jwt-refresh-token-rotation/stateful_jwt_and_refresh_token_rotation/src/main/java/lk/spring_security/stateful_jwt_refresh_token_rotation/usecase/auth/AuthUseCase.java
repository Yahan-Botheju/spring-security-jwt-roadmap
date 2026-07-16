package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;

public interface AuthUseCase {

    //register user
    void registerUser(User user);

    //login user
    AuthResult loginUser(String email, String password, HttpServletResponse httpServletResponse);
}
