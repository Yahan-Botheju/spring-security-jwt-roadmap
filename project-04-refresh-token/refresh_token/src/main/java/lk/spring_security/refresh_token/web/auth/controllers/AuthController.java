package lk.spring_security.refresh_token.web.auth.controllers;

import lk.spring_security.refresh_token.usecase.auth.AuthUseCase;
import lk.spring_security.refresh_token.web.auth.webMappers.AuthWebMapper;
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
}
