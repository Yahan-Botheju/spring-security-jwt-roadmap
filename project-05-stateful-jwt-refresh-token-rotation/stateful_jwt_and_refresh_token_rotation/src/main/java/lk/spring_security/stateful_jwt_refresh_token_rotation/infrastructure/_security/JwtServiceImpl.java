package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security;

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

    /* __GENERATE TOKENS__ */

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



     /* __JWT HELPER METHODS__ */

    //common token builder method
     private String buildToken(Map<String, Object> extraClaims, String subject, long expirationMs) {
        return  Jwts.builder()
                .claims(extraClaims)
                .subject(subject)
                .issuedAt(new Data(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();
     }

    /* __VALIDATION METHODS__ */


}
