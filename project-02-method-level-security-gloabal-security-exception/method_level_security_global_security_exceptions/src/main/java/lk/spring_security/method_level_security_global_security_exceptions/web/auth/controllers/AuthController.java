package lk.spring_security.method_level_security_global_security_exceptions.web.auth.controllers;

import jakarta.validation.Valid;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.AuthUseCase;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.LoginResult;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterCommand;
import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.records.RegisterResult;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.DTOs.*;
import lk.spring_security.method_level_security_global_security_exceptions.web.auth.webMappers.AuthWebMapper;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<RegisterResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO registerRequestDTO
    ){
        //turn to command
        RegisterCommand toCommand = authWebMapper.toRegisterCommand(registerRequestDTO);
        //set command to usecase
        RegisterResult toUseCase = authUseCase.register(toCommand);
        //result to response
        RegisterResponseDTO responseDTO = authWebMapper.toRegisterResponseDTO(toUseCase);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //user login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO
    ){
        LoginCommand toCommand = authWebMapper.toLoginCommand(loginRequestDTO);
        LoginResult toUseCase = authUseCase.login(toCommand);
        LoginResponseDTO responseDTO = authWebMapper.toLoginResponseDTO(toUseCase);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
