package lk.spring_security.refresh_token.web.auth.controllers;

import jakarta.validation.Valid;
import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.usecase.auth.AuthUseCase;
import lk.spring_security.refresh_token.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.refresh_token.web.auth.DTOs.AuthResponseDTO;
import lk.spring_security.refresh_token.web.auth.webMappers.AuthWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //inject required dependencies
    private final AuthUseCase authUseCase;
    private final AuthWebMapper authWebMapper;

    public AuthController(
            AuthUseCase authUseCase,
            AuthWebMapper authWebMapper
    ) {
        this.authUseCase = authUseCase;
        this.authWebMapper = authWebMapper;
    }

    //register user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @Valid @RequestBody AuthRequestDTO authRequestDTO
            ){
        User toDomainModel = authWebMapper.toDomainModel(authRequestDTO);
        authUseCase.registerUser(toDomainModel);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}
