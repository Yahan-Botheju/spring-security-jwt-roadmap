package lk.spring_security.refresh_token.web._shared.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {

    //initiate token existing time
   @Value("${application.security.cookie.access-token-expiry-seconds}")
    private int accessTokenExpiry;

   @Value("${application.security.cookie.refresh-token-expiry-seconds}")
    private int refreshTokenExpiry;
}
