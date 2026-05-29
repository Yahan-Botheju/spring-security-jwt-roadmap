package lk.spring_security.method_level_security_global_security_exceptions.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.spring_security.method_level_security_global_security_exceptions.domain.services.JwtService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    //generate token with extra details
    @Override
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(createJavaSecretObject())
                .compact();
    }

    /* ----- VALIDATION ----- */

    //extract token data
    private Claims extractTokenData(String token){
        return Jwts.parser()
                .verifyWith(createJavaSecretObject())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //generic type that return related data of token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractTokenData(token);
        return claimsResolver.apply(claims);
    }

    /* validate using token data  */

    //get username from token
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //get token expire data
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
