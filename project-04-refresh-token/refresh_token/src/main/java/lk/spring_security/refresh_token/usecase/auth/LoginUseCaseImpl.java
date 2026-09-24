package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.repositories.*;
import lk.spring_security.refresh_token.usecase.auth.records.LoginCommand;
import lk.spring_security.refresh_token.usecase.auth.records.LoginResult;

public class LoginUseCaseImpl implements LoginUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieService cookieService;
    private final IdentityProvider identityProvider;

    public LoginUseCaseImpl(
            UserRepository userRepository,
            TokenService tokenService,
            RefreshTokenRepository refreshTokenRepository,
            CookieService cookieService,
            IdentityProvider identityProvider
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.cookieService = cookieService;
        this.identityProvider = identityProvider;
    }

    //login user
    @Override
    public LoginResult login(LoginCommand loginCommand) {



        return null;
    }
}
