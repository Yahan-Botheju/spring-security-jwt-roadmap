package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.repositories.CookieService;
import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;

public class LoginUseCaseImpl implements LoginUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieService cookieService;

    public LoginUseCaseImpl(
            UserRepository userRepository,
            TokenService tokenService,
            RefreshTokenRepository refreshTokenRepository,
            CookieService cookieService
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.cookieService = cookieService;
    }
}
