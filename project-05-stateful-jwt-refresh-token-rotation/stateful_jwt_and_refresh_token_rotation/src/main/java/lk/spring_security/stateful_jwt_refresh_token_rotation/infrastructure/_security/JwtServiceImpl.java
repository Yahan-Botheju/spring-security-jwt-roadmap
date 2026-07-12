package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;

import javax.crypto.SecretKey;

public class JwtServiceImpl implements TokenService {

    //inject required dependencies
    private final SecretKey secretKey;

    public JwtServiceImpl(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
