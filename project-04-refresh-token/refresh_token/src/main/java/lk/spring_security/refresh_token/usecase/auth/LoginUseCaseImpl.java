package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.models.RefreshToken;
import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.domain.repositories.*;
import lk.spring_security.refresh_token.usecase.auth.records.LoginCommand;
import lk.spring_security.refresh_token.usecase.auth.records.LoginResult;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Instant;
import java.util.UUID;

public class LoginUseCaseImpl implements LoginUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieService cookieService;
    private final IdentityProvider identityProvider;

    //inject token expiration time
    private final long refreshTokenExpirationMs;

    public LoginUseCaseImpl(
            UserRepository userRepository,
            TokenService tokenService,
            RefreshTokenRepository refreshTokenRepository,
            CookieService cookieService,
            IdentityProvider identityProvider,
            long refreshTokenExpirationMs
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.cookieService = cookieService;
        this.identityProvider = identityProvider;
        this.refreshTokenExpirationMs = refreshTokenExpirationMs;
    }

    //login user
    @Override
    public LoginResult login(LoginCommand loginCommand) {

        //check given username and password are correct via auth provider
        identityProvider.authenticate(loginCommand.email(), loginCommand.password());

        //get user from db
        User exstingUser = userRepository.findByEmail(loginCommand.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //create access token (15m)
        String accessToken = tokenService.generateToken(exstingUser);

        //create refresh token as UUID string
        String refreshTokenStr = UUID.randomUUID().toString();

        RefreshToken newRefreshToken = RefreshToken.createRefreshToken(
                refreshTokenStr,
                Instant.now().minusSeconds()
        )

        return null;
    }
}
