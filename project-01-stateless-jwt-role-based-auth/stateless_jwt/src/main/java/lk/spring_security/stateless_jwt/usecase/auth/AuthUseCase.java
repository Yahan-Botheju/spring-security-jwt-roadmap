package lk.spring_security.stateless_jwt.usecase.auth;

import lk.spring_security.stateless_jwt.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.stateless_jwt.web.auth.DTOs.AuthResponseDTO;

public interface AuthUseCase {
    //initiate auth request
    AuthResponseDTO register(AuthRequestDTO authRequestDTO);
    //initiate auth response
    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
}
