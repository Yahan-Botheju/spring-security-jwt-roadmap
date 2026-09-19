package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.IdentityProvider;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.UserRepository;

public class LoginUserUseCaseImpl implements LoginUserUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final IdentityProvider identityProvider;
    private final CookieService cookieService;

    public LoginUserUseCaseImpl(
            UserRepository userRepository,
            TokenService tokenService,
            IdentityProvider identityProvider,
            CookieService cookieService
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.identityProvider = identityProvider;
        this.cookieService = cookieService;
    }
}
