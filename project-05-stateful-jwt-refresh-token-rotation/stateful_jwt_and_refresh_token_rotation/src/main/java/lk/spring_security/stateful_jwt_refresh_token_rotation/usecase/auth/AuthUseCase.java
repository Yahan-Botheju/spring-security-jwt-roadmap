package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.records.AuthenticatedUser;

public interface AuthUseCase {

    //register user
    void registerUser(User user);

    //login user
    AuthenticatedUser loginUser(String email, String password, HttpServletResponse httpServletResponse);

    //logout
    void logout(HttpServletResponse httpServletResponse);

    //refresh token
    AuthenticatedUser refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
