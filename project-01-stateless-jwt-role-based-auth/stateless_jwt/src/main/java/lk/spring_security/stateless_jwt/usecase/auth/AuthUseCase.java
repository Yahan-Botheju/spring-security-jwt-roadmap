package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.web.auth.AuthRequestDTO;
import lk.spring_security.stateless_jwt.web.auth.AuthResponseDTO;

public interface AuthUseCase {
    //initiate auth request
    AuthResponseDTO register(AuthRequestDTO authRequestDTO);
    //initiate auth response
    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
}
