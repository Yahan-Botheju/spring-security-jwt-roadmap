package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.repositories.UserRepository;
import lk.spring_security.refresh_token.web._shared.services.CookieService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required dependencies
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CookieService cookieService;
    private AuthenticationManager authenticationManager;


    public AuthUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cookieService = cookieService;
        this.authenticationManager = authenticationManager;
    }
}
