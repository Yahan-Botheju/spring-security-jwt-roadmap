package lk.spring_security.cookie_based_jwt_auth.infrastructure._security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.spring_security.cookie_based_jwt_auth.domain.services.JwtService;

import javax.crypto.SecretKey;

public class JwtImpl implements JwtService {

    //inject secret key
    private final String SECRET_KEY;

    public JwtImpl(String secretKey) {
        this. SECRET_KEY = secretKey;
    }

    //create java secret key object
    private SecretKey createJavaSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
