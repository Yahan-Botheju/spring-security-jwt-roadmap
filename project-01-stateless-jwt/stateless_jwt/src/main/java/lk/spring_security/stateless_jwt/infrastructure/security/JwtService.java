package lk.spring_security.stateless_jwt.infrastructure.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


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

    //create generics type method that return data from CLAIM
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /* ----- PUBLIC METHODS/ LOGICS ----- */

    //get username from token
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //get expire data of token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //check token is expired or not
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /* ----- CREATE NEW TOKEN IN USER LOGIN ----- */

    //get user details for create token
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    //generate token with extra details added
    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .claims(extractClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey())
                .compact();
    }
}
