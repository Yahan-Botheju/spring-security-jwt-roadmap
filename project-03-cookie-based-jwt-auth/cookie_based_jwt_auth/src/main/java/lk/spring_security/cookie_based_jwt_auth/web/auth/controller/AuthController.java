package lk.spring_security.cookie_based_jwt_auth.web.auth.controller;

import lk.spring_security.cookie_based_jwt_auth.domain.services.CookieService;
import lk.spring_security.cookie_based_jwt_auth.usecase.auth.AuthUseCase;
import lk.spring_security.cookie_based_jwt_auth.web.auth.webMapper.AuthWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //inject required classes
    private final AuthUseCase authUseCase;
    private final CookieService cookieService;
    private final AuthWebMapper authWebMapper;
}
