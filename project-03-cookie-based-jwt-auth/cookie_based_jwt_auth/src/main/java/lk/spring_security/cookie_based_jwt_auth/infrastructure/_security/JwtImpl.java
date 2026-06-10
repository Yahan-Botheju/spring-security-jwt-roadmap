package lk.spring_security.cookie_based_jwt_auth.infrastructure._security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.spring_security.cookie_based_jwt_auth.domain.models.User;
import lk.spring_security.cookie_based_jwt_auth.domain.services.JwtService;
import lk.spring_security.cookie_based_jwt_auth.infrastructure._security.user_spring_wrapper.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtImpl implements JwtService {

    //inject secret key
    private final String SECRET_KEY;

    public JwtImpl(String secretKey) {
        this. SECRET_KEY = secretKey;
    }

    //create java secret key object
    private SecretKey createJavaSecretKeyObject(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /* ----- __GENERATE_TOKEN__ ----- */

    //create general token without extra details (spring security user details included)
    @Override
    public String generateToken(User user){
        return generateToken(new HashMap<>(), user);
    }

    //create token with extra details included
    @Override
    public String generateToken(Map<String,Object> extractClaims, User user){
        //wrap user from custom user details
        CustomUserDetails userDetails = new CustomUserDetails(user);

        //create token with extra details included of user
        return Jwts.builder()
                .claims(extractClaims) //allow to add extra details related to user
                .subject(userDetails.getUsername()) // add username to token
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(createJavaSecretKeyObject())
                .compact();
    }

    /* -----  __TOKEN_EXTRACTION_METHODS__ ----- */

    //get username from token
    @Override
    public String extractUserName(String token){
        //read token data JWT claim object
        Claims claims = Jwts.parser()
                .verifyWith(createJavaSecretKeyObject())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        //return only username
        return claims.getSubject();
    }

    //get expired data from token
    private Date extractExpiration(String token){
        Claims claims = Jwts.parser()
                .verifyWith(createJavaSecretKeyObject())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        //return only expiration data
        return claims.getExpiration();
    }


    /* ----- __VALIDATION_METHODS__ ----- */


    //check token is expired or not
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //create token validation method
    @Override
    public boolean validateToken(String token, UserDetails userDetails){
        //extract username from token
        final String username = extractUserName(token);
        //check username is equal to db username and token is not expired
        return (username.equals(userDetails.getUsername() ) && isTokenExpired(token));
    }

}
