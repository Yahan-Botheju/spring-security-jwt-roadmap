package lk.spring_security.stateful_jwt_refresh_token_rotation.infrastructure._security.filter;

import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.CookieService;
import lk.spring_security.stateful_jwt_refresh_token_rotation.domain.repositories.TokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //inject required dependencies
    private final CookieService cookieService;
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            CookieService cookieService,
            TokenService tokenService,
            UserDetailsService userDetailsService
    ) {
        this.cookieService = cookieService;
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }
}
