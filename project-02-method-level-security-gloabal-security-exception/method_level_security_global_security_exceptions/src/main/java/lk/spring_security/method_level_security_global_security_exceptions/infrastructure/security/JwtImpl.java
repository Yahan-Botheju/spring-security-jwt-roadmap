package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.HashMap;

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

    //get user details from spring interface then assign to create token method
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
}
