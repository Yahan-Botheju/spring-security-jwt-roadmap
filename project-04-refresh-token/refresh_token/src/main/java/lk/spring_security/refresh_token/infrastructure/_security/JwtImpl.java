package lk.spring_security.refresh_token.infrastructure._security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JwtImpl implements TokenService {

    //inject required dependencies
    private final SecretKey secretKey;
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000;

    public JwtImpl(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    /* ----- __GENERATE_TOKEN__ ----- */

    //create token extractor
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }



    @Override
    public String extractUsername(String token) {
        return "";
    }

    @Override
    public String generateAccessToken(UserDetails userDetails, Long userId) {
        return "";
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }
}
