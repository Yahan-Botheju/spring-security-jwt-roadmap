package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.models.User;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.user_spring_wrapper.CustomUserDetailsService;
import lombok.Data;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    /* __GENERATE_TOKENS__ */

    //generate access token
    @Override
    public String generateAccessToken(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().name()); //include role
        extraClaims.put("userId",  user.getUserId()); //include user ID

        //wrap using spring custom user details
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return buildToken(extraClaims, customUserDetails.getUsername(), accessTokenExpirationMs);
    }

    //generate refresh token
    @Override
    public String generateRefreshToken(User user){
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return buildToken(new  HashMap<>(), customUserDetails.getUsername(), refreshTokenExpirationMs);
    }


     /* __JWT_HELPER_METHODS__ */

    //common token builder method
     private String buildToken(Map<String, Object> extraClaims, String subject, long expirationMs) {
        return  Jwts.builder()
                .claims(extraClaims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();
     }

    /* __TOKEN_EXTRACTION_METHODS__ */

    //create parser API
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //create generic for get data
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //extract email
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /* __VALIDATION_METHODS__ */

    //create token expiration
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
