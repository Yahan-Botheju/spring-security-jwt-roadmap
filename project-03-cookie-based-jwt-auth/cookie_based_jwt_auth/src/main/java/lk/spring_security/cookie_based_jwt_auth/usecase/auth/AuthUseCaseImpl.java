package lk.spring_security.cookie_based_jwt_auth.usecase.auth;

import lk.spring_security.cookie_based_jwt_auth.domain.repositories.UserRepository;
import lk.spring_security.cookie_based_jwt_auth.domain.services.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required classes
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //inject
}
