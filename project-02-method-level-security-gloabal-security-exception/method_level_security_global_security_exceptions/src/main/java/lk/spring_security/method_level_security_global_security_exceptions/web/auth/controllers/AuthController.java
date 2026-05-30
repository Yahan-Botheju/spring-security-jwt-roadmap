package lk.spring_security.method_level_security_global_security_exceptions.web.auth.controllers;

import lk.spring_security.method_level_security_global_security_exceptions.usecase.auth.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {

    //inject auth usecase
    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }
}
