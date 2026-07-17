package lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth.AuthUseCase;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.DTOs.AuthResponseDTO;
import lk.spring_security.stateful_jwt_refresh_token_rotation.web.auth.webMapper.AuthWebMapper;
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

    //login user
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @Valid @RequestBody AuthRequestDTO authRequestDTO,
            HttpServletResponse httpServletResponse
    ){
        //get token
        var tokenResponse = authUseCase.loginUser(
                authRequestDTO.getEmail(),
                authRequestDTO.getPassword(),
                httpServletResponse);
    }
}
