package lk.spring_security.method_level_security_global_security_exceptions.web.auth.controllers;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.AuthUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.AuthResponseDTO;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.webMappers.AuthWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {

    //inject required classes
    private final AuthUseCase authUseCase;
    private final AuthWebMapper authWebMapper;

    public AuthController(AuthUseCase authUseCase,  AuthWebMapper authWebMapper) {
        this.authUseCase = authUseCase;
        this.authWebMapper = authWebMapper;
    }

    //register new user
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody AuthRequestDTO authRequestDTO
    ){

        return ResponseEntity.ok(authUseCase.registerUser(authRequestDTO));
    }

    //user login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody AuthRequestDTO authRequestDTO
    ){
        return ResponseEntity.ok(authUseCase.loginUser(authRequestDTO));
    }
}
