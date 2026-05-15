package lk.spring_security.stateless_jwt.infrastructure.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;


@Service
public class JwtService {

    //create secret
    @Value("${application.security.jwt.secret-key}")
    private  String SECRET_KEY;


    /* ----- HELPER METHODS ----- */

    //turn KEY(String) to Java secretKey object
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //extract token data
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /* ----- PUBLIC METHODS ----- */


}
