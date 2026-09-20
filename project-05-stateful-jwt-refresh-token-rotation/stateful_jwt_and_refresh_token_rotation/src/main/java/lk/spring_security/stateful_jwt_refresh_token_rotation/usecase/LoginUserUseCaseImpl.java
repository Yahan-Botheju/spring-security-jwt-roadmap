package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;

public class LoginUserUseCaseImpl extends GenerateRefreshTokenSupport implements LoginUserUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final IdentityProvider identityProvider;
    private final CookieService cookieService;

    public LoginUserUseCaseImpl(
            UserRepository userRepository,
            TokenService tokenService,
            IdentityProvider identityProvider,
            CookieService cookieService,
            RefreshTokenRepository refreshTokenRepository
    ) {
        super(refreshTokenRepository);
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.identityProvider = identityProvider;
        this.cookieService = cookieService;
    }

    public void loginUser(String email, String password){

        //check email password correctness through auth provider
        identityProvider.authenticate(email, password);

        //get user from db
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        //generate tokens
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);
    }
}
