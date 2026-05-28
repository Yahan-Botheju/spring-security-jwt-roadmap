package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

public class JwtImpl implements JwtService {

    //inject secret, then create constructor
    private final String secret_key;

    public JwtImpl(String secretKey) {
        this.secret_key = secretKey;
    }

    /* ----- CREATE NEW TOKEN ----- */

    //set key into java secret obj
    private SecretKey createJavaSecretObject() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
