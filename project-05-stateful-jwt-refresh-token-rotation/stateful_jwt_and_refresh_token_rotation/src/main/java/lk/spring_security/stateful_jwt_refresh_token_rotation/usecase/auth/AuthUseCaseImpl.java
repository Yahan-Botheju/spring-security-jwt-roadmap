package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.RefreshTokenReposiroty;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCaseImpl implements AuthUseCase{

    //inject required dependencies
    private final RefreshTokenReposiroty refreshTokenReposiroty;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieService cookieService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthUseCaseImpl(
            RefreshTokenReposiroty refreshTokenReposiroty,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CookieService cookieService,
            TokenService tokenService,
            AuthenticationManager authenticationManager
    ) {
        this.refreshTokenReposiroty = refreshTokenReposiroty;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cookieService = cookieService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }
}
