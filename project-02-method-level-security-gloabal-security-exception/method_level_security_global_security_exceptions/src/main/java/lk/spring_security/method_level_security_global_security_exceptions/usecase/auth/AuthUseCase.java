package lk.spring_security.method_level_security_global_security_exceptions.usecase.auth;

import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthResponseDTO;

public interface AuthUseCase {
    //initiate register user
    AuthResponseDTO registerUser(AuthRequestDTO authRequestDTO);

    //initiate login user
    AuthResponseDTO loginUser(AuthRequestDTO authRequestDTO);
}
