package lk.spring_security.refresh_token.infrastructure._security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lk.spring_security.refresh_token.domain.models.User;
import lk.spring_security.refresh_token.domain.repositories.TokenService;
import lk.spring_security.refresh_token.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtImpl implements TokenService {

    //inject required dependencies
    private final SecretKey secretKey;

    //limit token to 15m
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000;

    public JwtImpl(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    /* ----- __GENERATE_TOKEN__ ----- */

    //create general token without extra details included
    @Override
    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    //generate token with extra details included
    @Override
    public String generateToken(Map<String, Object> extractClaims, User user) {
        //wrap user details related to spring security
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return Jwts.builder()
                .claims(extractClaims)
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(secretKey)
                .compact();
    }

    /* ----- __TOKEN_EXTRACTION_METHODS__ ----- */

    //get username from token
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //initiate generic to get data
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //create token extractor
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /* ----- __VALIDATION_METHODS__ ----- */


    //create token expiration
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
