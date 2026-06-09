package lk.spring_security.cookie_based_jwt_auth.infrastructure._security;

import io.jsonwebtoken.Claims;
import lk.spring_security.cookie_based_jwt_auth.domain.services.JwtService;

public class JwtImpl implements JwtService {

    //inject secret key
    private final String SECRET_KEY;

    public JwtImpl(String secretKey) {
        this. SECRET_KEY = secretKey;
    }
}
