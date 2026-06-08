package lk.spring_security.cookie_based_jwt_auth.web._shared.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookieService {


    /* ----- CREATE COOKIE ----- */

    //create cookie name
    @Value("${app.security.cookie-name}")
    private String cookieName;

    //initiate expire time
    private static final int COOKIE_EXPIRY = 24 * 60 * 60;


}
