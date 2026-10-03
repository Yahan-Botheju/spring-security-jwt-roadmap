package lk.spring_security.stateless_jwt.web.auth.controllers;

import jakarta.validation.Valid;
import lk.spring_security.stateless_jwt.usecase.auth.AuthUseCase;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.LoginResult;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterCommand;
import lk.spring_security.stateless_jwt.usecase.auth.records.RegisterResult;
import lk.spring_security.stateless_jwt.web.auth.DTOs.*;
import lk.spring_security.stateless_jwt.web.auth.webMapper.AuthWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    //inject auth usecase
    private final AuthUseCase authUseCase;
    private final AuthWebMapper authWebMapper;


    //register new user
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO registerRequestDTO
    ){
        RegisterCommand toRegisterCommand = authWebMapper.toRegisterCommand(registerRequestDTO);
        RegisterResult registerResult = authUseCase.register(toRegisterCommand);
        RegisterResponseDTO registerResponseDTO = authWebMapper.toRegisterResponseDTO(registerResult);

        return  ResponseEntity.status(HttpStatus.OK).body(registerResponseDTO);
    }

    //user login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO
    ){
        LoginCommand toLoginCommand = authWebMapper.toLoginCommand(loginRequestDTO);
        LoginResult loginResult = authUseCase.login(toLoginCommand);
        LoginResponseDTO loginResponseDTO = authWebMapper.toLoginResponseDTO(loginResult);

        return  ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
    }
}
