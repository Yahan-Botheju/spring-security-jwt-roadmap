package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;

import javax.crypto.SecretKey;

public class JwtServiceImpl implements TokenService {

    //inject required dependencies
    private final SecretKey secretKey;
    private final Long accessTokenExpirationMs;
    private final Long refreshTokenExpirationMs;

    public JwtServiceImpl(
            SecretKey secretKey,
            long accessTokenExpirationMs,
            long refreshTokenExpirationMs

    ) {
        this.secretKey = secretKey;
        this.accessTokenExpirationMs = accessTokenExpirationMs;
        this.refreshTokenExpirationMs = refreshTokenExpirationMs;
    }
}
