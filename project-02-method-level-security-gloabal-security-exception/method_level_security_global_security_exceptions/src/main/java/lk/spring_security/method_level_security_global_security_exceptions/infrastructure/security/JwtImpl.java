package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security;

import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;

public class JwtImpl implements JwtService {

    //inject secret, then create constructor
    private final String secret_key;

    public JwtImpl(String secretKey) {
        this.secret_key = secretKey;
    }
}
