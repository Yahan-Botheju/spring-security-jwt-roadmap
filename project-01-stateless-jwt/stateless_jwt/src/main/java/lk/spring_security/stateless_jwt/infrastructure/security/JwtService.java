package lk.spring_security.stateless_jwt.infrastructure.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    //create secret
    @Value("${application.security.jwt.secret-key}")
    private  String SECRET_KEY;


}
