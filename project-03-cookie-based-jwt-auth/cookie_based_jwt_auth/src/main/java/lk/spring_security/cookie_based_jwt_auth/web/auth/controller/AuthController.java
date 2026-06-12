package lk.spring_security.cookie_based_jwt_auth.web.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lk.spring_security.cookie_based_jwt_auth.domain.services.CookieService;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.AuthUseCase;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.AuthRequestDTO;
import lk.spring_security.cookie_based_jwt_auth.web.auth.DTOs.AuthResponseDTO;
import lk.spring_security.cookie_based_jwt_auth.web.auth.webMapper.AuthWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //inject required classes
    private final AuthUseCase authUseCase;
    private final CookieService cookieService;
    private final AuthWebMapper authWebMapper;

    public AuthController(
            AuthUseCase authUseCase,
            CookieService cookieService,
            AuthWebMapper authWebMapper
    ) {
        this.authUseCase = authUseCase;
        this.cookieService = cookieService;
        this.authWebMapper = authWebMapper;
    }

    //register user
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(
            @Valid @RequestBody AuthRequestDTO authRequestDTO,
            HttpServletRequest request //set cookie to get response
            ){}

}
