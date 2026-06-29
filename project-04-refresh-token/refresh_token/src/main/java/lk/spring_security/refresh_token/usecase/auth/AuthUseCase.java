package lk.spring_security.refresh_token.usecase.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.spring_security.refresh_token.domain.models.User;

public interface AuthUseCase {

    //register new user
    User registerUser(User user);

    //login user
    void loginUser(String email, String password, HttpServletResponse httpServletResponse);

    //create new access token by using refresh token
    void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    //logout user
    void logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
