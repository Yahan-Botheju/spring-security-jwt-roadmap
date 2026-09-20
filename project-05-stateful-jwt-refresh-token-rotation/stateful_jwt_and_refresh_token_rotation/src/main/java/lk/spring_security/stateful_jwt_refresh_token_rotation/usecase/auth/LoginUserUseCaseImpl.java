package lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.auth;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.*;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.LoginUserCommand;
import lk.spring_security.stateful_jwt_refresh_token_rotation.usecase.records.LoginUserResult;

public class LoginUserUseCaseImpl extends GenerateRefreshTokenSupport implements LoginUserUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final IdentityProvider identityProvider;

    public LoginUserUseCaseImpl(
            UserRepository userRepository,
            TokenService tokenService,
            IdentityProvider identityProvider,
            RefreshTokenRepository refreshTokenRepository
    ) {
        super(refreshTokenRepository);
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.identityProvider = identityProvider;
    }

    @Override
    public LoginUserResult loginUser(LoginUserCommand loginUserCommand) {

        //check email password correctness through auth provider
        identityProvider.authenticate(loginUserCommand.email(), loginUserCommand.password());

        //get user from db
        User user = userRepository.findByEmail(loginUserCommand.email())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        //generate tokens
        String accessToken = tokenService.generateAccessToken(user);
        String refreshToken = tokenService.generateRefreshToken(user);

        //user abstract method
        generateRefreshToken(user, refreshToken);

        return new LoginUserResult(
                accessToken,
                refreshToken,
                user.getUserId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
