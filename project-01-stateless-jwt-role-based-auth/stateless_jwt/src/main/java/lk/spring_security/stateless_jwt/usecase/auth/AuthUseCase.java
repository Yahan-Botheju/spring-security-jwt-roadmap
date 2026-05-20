package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.web.auth.AuthRequest;
import lk.spring_security.stateless_jwt.web.auth.AuthResponse;

public interface AuthUseCase {
    //initiate auth request
    AuthResponse register(AuthRequest authRequest);
    //initiate auth response
    AuthResponse login(AuthRequest authRequest);
}
