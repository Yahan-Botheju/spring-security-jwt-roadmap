package lk.spring_security.refresh_token.usecase.auth;

import lk.spring_security.refresh_token.domain.repositories.RefreshTokenRepository;
import lk.spring_security.refresh_token.domain.repositories.UserRepository;

public class LogoutUseCaseImpl implements LogoutUseCase {

    //inject required dependencies
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUseCaseImpl(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }
}
